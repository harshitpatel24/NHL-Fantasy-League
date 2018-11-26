import json

def main():
	teamDict={'Atlanta Flames': 'AFM', 'Anaheim Ducks': 'ANA', 'Arizona Coyotes': 'ARI', 'Atlanta Thrashers': 'ATL', 'Boston Bruins': 'BOS', 'Brooklyn Americans': 'BRK', 'Buffalo Sabres': 'BUF', 'Carolina Hurricanes': 'CAR', 'California Golden Seals': 'CGS', 'Calgary Flames': 'CGY', 'Chicago Blackhawks': 'CHI','Columbus Blue Jackets': 'CBJ', 'Cleveland Barons': 'CLE', 'Colorado Rockies': 'CLR', 'Colorado Avalanche': 'COL', 'Dallas Stars': 'DAL', 'Detroit Falcons': 'DFL', 'Detroit Cougars': 'DCG', 'Detroit Red Wings': 'DET', 'Edmonton Oilers': 'EDM', 'Florida Panthers': 'FLA', 'Hamilton Tigers': 'HAM', 'Hartford Whalers': 'HFD', 'Kansas City Scouts': 'KCS', 'Los Angeles Kings': 'LAK', 'Minnesota Wild': 'MIN', 'Montreal Maroons': 'MMR', 'Minnesota North Stars': 'MNS', 'Montreal Canadiens': 'MTL', 'Montreal Wanderers': 'MWN', 'Nashville Predators': 'NSH', 'New Jersey Devils': 'NJD', 'New York Americans': 'NYA', 'New York Islanders': 'NYI', 'New York Rangers': 'NYR', 'Oakland Seals': 'OAK', 'Ottawa Senators': 'OTT', 'Philadelphia Flyers': 'PHI', 'Phoenix Coyotes': 'PHX', 'Pittsburgh Pirates': 'PIR', 'Pittsburgh Penguins': 'PIT', 'Philadelphia Quakers': 'QUA', 'Quebec Nordiques': 'QUE', 'Quebec Bulldogs': 'QBD', 'Ottawa Senators (original)': 'SEN', 'San Jose Sharks': 'SJS', 'St. Louis Eagles': 'SLE', 'St. Louis Blues': 'STL', 'Toronto Arenas': 'TAN', 'Tampa Bay Lightning': 'TBL', 'Toronto Maple Leafs': 'TOR', 'Toronto St. Patricks': 'TSP', 'Vancouver Canucks': 'VAN', 'Vegas Golden Knights': 'VEG', 'Winnipeg Jets (original)': 'WIN', 'Winnipeg Jets': 'WPG', 'Washington Capitals': 'WSH'}
	with open('Top200Player.json','r') as top200Data:
		top200 = json.load(top200Data)
	with open('PlayersData.json','r') as allPlayersData:
		allPlayers = json.load(allPlayersData)
	#print(top200)
	count = 0
	finalPlayersList=[]
	
	#polishing top 200 players
	for player in top200:
		playerDict={}
		flag=False
		for playerWithFullData in allPlayers:
			if player['player_name'] == playerWithFullData['playerName']:
				count = count + 1
				playerTeam = list(teamDict.keys())[list(teamDict.values()).index(player['team'])]
				playerDict['rank'] = player['rank']
				playerDict['playerName'] = player['player_name']
				playerDict['teamName'] = playerTeam
				playerDict['teamAbbrevation'] = player['team']
				playerDict['tShirtNumber']= playerWithFullData['tShirtNumber']
				playerDict['position']= player['position']
				playerDict['age']= playerWithFullData['age']
				playerDict['height']= playerWithFullData['height']
				playerDict['weight']= playerWithFullData['weight']
				playerDict['birthDate']= playerWithFullData['birthDate']
				finalPlayersList.append(playerDict)
				flag = True
				break
		if flag == False:
			playerTeam = list(teamDict.keys())[list(teamDict.values()).index(player['team'])]
			playerDict['rank'] = player['rank']
			playerDict['playerName'] = player['player_name']
			playerDict['teamName'] = playerTeam
			playerDict['teamAbbrevation'] = player['team']
			playerDict['tShirtNumber']= 24
			playerDict['position']= player['position']
			playerDict['age']= 28
			playerDict['height']= '6-2'
			playerDict['weight']= 220
			playerDict['birthDate']= 'March 17, 1990'
			finalPlayersList.append(playerDict)
	tempTop200=finalPlayersList
	
	#adding rest after top 200
	for playerWithFullData in allPlayers:
		playerDict={}
		flag = False
		for tempPlayer in tempTop200:
			if tempPlayer['playerName'] == playerWithFullData['playerName']:
				flag = True
				break
		if flag == False:
			playerDict['rank'] = 999
			playerDict['playerName'] = playerWithFullData['playerName']
			playerDict['teamName'] = playerWithFullData['teamName']
			playerDict['teamAbbrevation'] = teamDict[playerWithFullData['teamName']]
			playerDict['tShirtNumber']= playerWithFullData['tShirtNumber']
			playerDict['position']= playerWithFullData['position']
			playerDict['age']= playerWithFullData['age']
			playerDict['height']= playerWithFullData['height']
			playerDict['weight']= playerWithFullData['weight']
			playerDict['birthDate']= playerWithFullData['birthDate']
			finalPlayersList.append(playerDict)
	
	
	with open('final758PlayersData.json', 'w') as outfile:
		json.dump(finalPlayersList, outfile)
if __name__ == '__main__':
	main()