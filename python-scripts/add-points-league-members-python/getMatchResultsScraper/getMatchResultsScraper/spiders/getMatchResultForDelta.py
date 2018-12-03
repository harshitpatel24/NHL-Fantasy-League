# -*- coding: utf-8 -*-
import scrapy
import urllib
from datetime import datetime
from datetime import timedelta
from getMatchResultsScraper.items import MatchResultItem

class GetmatchresultfordeltaSpider(scrapy.Spider):
	name = 'getMatchResultForDelta'
	allowed_domains = ['www.hockey-reference.com']
	start_urls = ['http://www.hockey-reference.com/']
	teamDict={'Atlanta Flames': 'AFM', 'Anaheim Ducks': 'ANA', 'Arizona Coyotes': 'ARI', 'Atlanta Thrashers': 'ATL', 'Boston Bruins': 'BOS', 'Brooklyn Americans': 'BRK', 'Buffalo Sabres': 'BUF', 'Carolina Hurricanes': 'CAR', 'California Golden Seals': 'CGS', 'Calgary Flames': 'CGY', 'Chicago Blackhawks': 'CHI','Columbus Blue Jackets': 'CBJ', 'Cleveland Barons': 'CLE', 'Colorado Rockies': 'CLR', 'Colorado Avalanche': 'COL', 'Dallas Stars': 'DAL', 'Detroit Falcons': 'DFL', 'Detroit Cougars': 'DCG', 'Detroit Red Wings': 'DET', 'Edmonton Oilers': 'EDM', 'Florida Panthers': 'FLA', 'Hamilton Tigers': 'HAM', 'Hartford Whalers': 'HFD', 'Kansas City Scouts': 'KCS', 'Los Angeles Kings': 'LAK', 'Minnesota Wild': 'MIN', 'Montreal Maroons': 'MMR', 'Minnesota North Stars': 'MNS', 'Montreal Canadiens': 'MTL', 'Montreal Wanderers': 'MWN', 'Nashville Predators': 'NSH', 'New Jersey Devils': 'NJD', 'New York Americans': 'NYA', 'New York Islanders': 'NYI', 'New York Rangers': 'NYR', 'Oakland Seals': 'OAK', 'Ottawa Senators': 'OTT', 'Philadelphia Flyers': 'PHI', 'Phoenix Coyotes': 'PHX', 'Pittsburgh Pirates': 'PIR', 'Pittsburgh Penguins': 'PIT', 'Philadelphia Quakers': 'QUA', 'Quebec Nordiques': 'QUE', 'Quebec Bulldogs': 'QBD', 'Ottawa Senators (original)': 'SEN', 'San Jose Sharks': 'SJS', 'St. Louis Eagles': 'SLE', 'St. Louis Blues': 'STL', 'Toronto Arenas': 'TAN', 'Tampa Bay Lightning': 'TBL', 'Toronto Maple Leafs': 'TOR', 'Toronto St. Patricks': 'TSP', 'Vancouver Canucks': 'VAN', 'Vegas Golden Knights': 'VEG', 'Winnipeg Jets (original)': 'WIN', 'Winnipeg Jets': 'WPG', 'Washington Capitals': 'WSH'}
	date=[]
	teamName1=[]
	teamName2=[]
	teamGoal1=[]
	teamGoal2=[]
	teamScorrer1=[]
	teamScorrer2=[]
	teamAssist1=[]
	teamAssist2=[]
	def parse(self, response):
		url="https://www.hockey-reference.com/leagues/NHL_2019_games.html"
		yield scrapy.Request(url,callback=self.parse_schedule_level)
		
	def parse_schedule_level(self,response):
		schedule_table=response.xpath('//*[@id="games"]/tbody/tr').extract()
		
		for i in range(1,len(schedule_table)):
		#for i in range(1,2):
			
			if response.xpath('//*[@id="games"]/tbody/tr['+str(i)+']/th/text()').extract() != []:
				date1=response.xpath('//*[@id="games"]/tbody/tr['+str(i)+']/th/text()').extract()
				if datetime.strptime(date1[0],"%Y-%m-%d") == datetime.now() - timedelta(4):
					self.date.append(date1)
				else:
					pass
			else:
				date1=response.xpath('//*[@id="games"]/tbody/tr['+str(i)+']/th/a/text()').extract()
				if datetime.strptime(date1[0],"%Y-%m-%d") == datetime.now() - timedelta(4):
					self.date.append(date1)
				else:
					pass
				self.date.append(response.xpath('//*[@id="games"]/tbody/tr['+str(i)+']/th/a/text()').extract())
			self.teamName1.append(response.xpath('//*[@id="games"]/tbody/tr['+str(i)+']/td[1]/a/text()').extract())
			self.teamName2.append(response.xpath('//*[@id="games"]/tbody/tr['+str(i)+']/td[3]/a/text()').extract())
			self.teamGoal1.append(response.xpath('//*[@id="games"]/tbody/tr['+str(i)+']/td[2]/text()').extract())
			self.teamGoal2.append(response.xpath('//*[@id="games"]/tbody/tr['+str(i)+']/td[4]/text()').extract())
			
			if self.date[i-1][0] == str((datetime.now() - timedelta(4)).strftime('%Y-%m-%d')):
				url=self.start_urls[0]+str(response.xpath('//*[@id="games"]/tbody/tr['+str(i)+']/th/a/@href').extract()[0])
				yield scrapy.Request(url,callback=self.parse_score_details,meta={'date':self.date[-1][0],'team1':self.teamName1[-1][0],'team2':self.teamName2[-1][0],'team1G':self.teamGoal1[-1][0],'team2G':self.teamGoal2[-1][0]})
			
			else:
				if self.date[i-1][0] == str(datetime.now().strftime('%Y-%m-%d')):
					break
				else:
					pass
				#time.sleep(2)
				#break
				
	
	def parse_score_details(self,response):
		data = MatchResultItem()
		count=2
		flag = False
		scoreSummary={}
		goalieSummary={}
		team1ScoringList=[]
		team2ScoringList=[]
		team1FullName=response.meta['team1']
		team2FullName=response.meta['team2']
		team1ShortName=self.teamDict[team1FullName]
		team2ShortName=self.teamDict[team2FullName]
		while True:
			tempDict={}
			time=response.xpath('//div[@id="all_scoring"]/div[2]/div/table/tr['+str(count)+']/td[1]/text()').extract()
			scoredTeamName=response.xpath('//div[@id="all_scoring"]/div[2]/div/table/tr['+str(count)+']/td[2]/a/text()').extract()
			participatedPlayersInGoal=response.xpath('//div[@id="all_scoring"]/div[2]/div/table/tr['+str(count)+']/td[3]/a/text()').extract()
			
			if time == [] and flag == True:
				break
			elif time == []:
				flag = True
			elif type((time[0])) == str:
				try:
					if type(int(time[0])) == int:
						break
					else:
						pass
				except:
					#time1=time[0]
					#playerScored=
					flag = False
					tempDict['time']= time[0]
					tempDict['scoredBy']= participatedPlayersInGoal[0]
					tempDict['AssistBy']=participatedPlayersInGoal[1:]
					if team1ShortName == scoredTeamName[0]:
						team1ScoringList.append(tempDict)
					else:
						team2ScoringList.append(tempDict)
			count = count + 1
		
		g1=response.xpath('//*[@id="'+self.teamDict[response.meta['team1']]+'_goalies"]/tbody/tr/td[1]/a/text()').extract()
		ga1=response.xpath('//*[@id="'+self.teamDict[response.meta['team1']]+'_goalies"]/tbody/tr/td[3]/text()').extract()
		sv1=response.xpath('//*[@id="'+self.teamDict[response.meta['team1']]+'_goalies"]/tbody/tr/td[5]/text()').extract()
		g2=response.xpath('//*[@id="'+self.teamDict[response.meta['team2']]+'_goalies"]/tbody/tr/td[1]/a/text()').extract()
		ga2=response.xpath('//*[@id="'+self.teamDict[response.meta['team2']]+'_goalies"]/tbody/tr/td[3]/text()').extract()
		sv2=response.xpath('//*[@id="'+self.teamDict[response.meta['team2']]+'_goalies"]/tbody/tr/td[5]/text()').extract()
		goalie1={}
		print("G1",g1)
		print(response.meta['team1'])
		print(response.meta['team2'])
		print("G2",g2)
		goalie1['GoalieName']=g1[0]
		goalie1['GoalAgainst']=ga1[0]
		goalie1['Saves']=sv1[0]
		goalie2={}
		goalie2['GoalieName']=g2[0]
		goalie2['GoalAgainst']=ga2[0]
		goalie2['Saves']=sv2[0]
		goalieSummary[response.meta['team1']]=goalie1
		goalieSummary[response.meta['team2']]=goalie2
		#print(team1ScoringList,'\n\n\n')
		#print(team2ScoringList)
		scoreSummary[team1FullName]=team1ScoringList
		scoreSummary[team2FullName]=team2ScoringList
		data['date']=response.meta['date']
		data['teamName1']=response.meta['team1']
		data['teamName2']=response.meta['team2']
		data['teamGoal1']=response.meta['team1G']
		data['teamGoal2']=response.meta['team2G']
		data['scoreSummary']=scoreSummary
		data['goalieSummary']=goalieSummary
		yield data
		
		#print(len(date))
		#print(len(team1))
		#print(len(team2))
		'''
		for i in range(len(schedule_table)-1):
			data={}
			data['date']=date[i]
			data['team1']=team1[i][0]
			data['team2']=team2[i][0]
			print(date[i],team1[i],team2[i])
			with open('data.json', 'a') as outfile: 
				json.dump(data, outfile)
			'''