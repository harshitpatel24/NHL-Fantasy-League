from __future__ import print_function
import mysql.connector
import json
import sys
import datetime
import argparse
class Singleton:

	__instance = None
	@staticmethod
	def getInstance():
		if Singleton.__instance == None:
			Singleton()
		return Singleton.__instance

	def __init__(self):
		if Singleton.__instance != None:
			raise Exception("This class is a singleton!")
		else:
			Singleton.__instance = self

	def getConnection(self,dbPassword):
		try:
			conn=mysql.connector.connect(user="root", password=dbPassword,host="localhost",database="NHLFantasyLeague")
			return conn
		except:
			print("Database Connection Failed.....")
			return

	def loadJson(self,fileName):
		with open(fileName,'r') as players:
			jsonObjectList = json.load(players)
		return jsonObjectList

	def checkAlreadyExists(self,jsonObject,table,cursor):
		if table == 'Player':
			cursor.execute("SELECT * FROM hockeyPlayer where name='{0}' and teamName='{1}' and playerRank={2}".format(jsonObject['playerName'],jsonObject['teamName'],jsonObject['rank']))
			player = cursor.fetchall()
			if player == []:
				return False
			else:
				return True
		elif table == 'Schedule':
			cursor.execute("SELECT * FROM matchSchedule where team1='{0}' and team2='{1}' and date='{2}'".format(jsonObject['team1'],jsonObject['team2'],jsonObject['date']))
			matchSchedule = cursor.fetchall()
			if matchSchedule == []:
				return False
			else:
				return True
		else:
			print("No Proper Table Given")
			return True

	def getPlayerValue(self,rank):
		rankRange = [1,10,20,50,100,150,200,999]
		playerValueRange = [12,8,6,5,3,2,1]
		for i in range(len(rankRange)-1):
			if rank >= rankRange[i] and rank <= rankRange[i+1]:
				return playerValueRange[i]
			
			
	def insretInToDb(self,connection,jsonObject,table):
		try:
			cursor = connection.cursor()
			if table == 'Player':
				if not self.checkAlreadyExists(jsonObject,table,cursor):
					try:
						playerValue = self.getPlayerValue(int(jsonObject['rank']))
						cursor.execute("INSERT INTO hockeyPlayer(name,teamName,teamAbbr,playerRank,position,tShirtNo,age,height,weight,birthday,playerValue) VALUES ('{0}','{1}','{2}',{3},'{4}',{5},{6},'{7}',{8},'{9}',{10})".format(jsonObject['playerName'],jsonObject['teamName'],jsonObject['teamAbbrevation'],jsonObject['rank'],jsonObject['position'],jsonObject['tShirtNumber'],jsonObject['age'],jsonObject['height'],jsonObject['weight'],jsonObject['birthDate'],playerValue))
					except:
						print('Error during Inserting Player')
					connection.commit()
				else:
					print(jsonObject['playerName'],'is already present')
			elif table == 'Schedule':
				if not self.checkAlreadyExists(jsonObject,table,cursor):
					try:
						cursor.execute("INSERT INTO matchSchedule(team1,team2,date) VALUES ('{0}','{1}','{2}')".format(jsonObject['team1'],jsonObject['team2'],datetime.datetime.strptime(jsonObject['date'],"%Y-%m-%d").date()))
						connection.commit()
					except:
						print('Error during Inserting Schedule')
				else:
					print(jsonObject['date'],jsonObject['team1'],jsonObject['team2'],'is already present')
			else:
				print("No Proper Table Given")
			cursor.close()
		except:
			print("Unable to create cursor")

	def saveJsonObjToHockeyPlayerTable(self,jsonObject,dbPassword):
		connection = self.getConnection(dbPassword)
		self.insretInToDb(connection,jsonObject,'Player')
		connection.close()
	
	def saveJsonObjToScheduleTable(self,jsonObject,dbPassword):
		connection = self.getConnection(dbPassword)
		self.insretInToDb(connection,jsonObject,'Schedule')
		connection.close()

	def iterateAndSavePlayerInDb(self,jsonObjectList,dbPassword):
		for jsonObject in jsonObjectList:
			self.saveJsonObjToHockeyPlayerTable(jsonObject,dbPassword)
	
	def iterateAndSaveScheduleInDb(self,jsonObjectList,dbPassword):
		for jsonObject in jsonObjectList:
			self.saveJsonObjToScheduleTable(jsonObject,dbPassword)

	def addPlayersToDb(self,fileName,dbPassword):
		jsonObjectList = self.loadJson(fileName)
		#print(jsonObjectList)
		#print(len(jsonObjectList))
		self.iterateAndSavePlayerInDb(jsonObjectList,dbPassword)
	
	def addScheduleToDb(self,fileName,dbPassword):
		jsonObjectList = self.loadJson(fileName)
		self.iterateAndSaveScheduleInDb(jsonObjectList,dbPassword)

def main():
	playersFile='final757PlayersData.json'
	scheduleFile='NHLFullSchedule.json'
	
	parser = argparse.ArgumentParser(description="NHL Database helper")
	
	parser.add_argument("--addPlayer", help="add Player", required=True)
	parser.add_argument("--addSchedule", help="add Schedule", required=True)
	parser.add_argument("--dbPassword", help="database password", required=True)

	args = parser.parse_args()
	if args.addPlayer == 'True' and args.addSchedule == 'True' and args.dbPassword:
		s = Singleton.getInstance()
		s.addPlayersToDb(playersFile,args.dbPassword)
		s.addScheduleToDb(scheduleFile,args.dbPassword)
	elif args.addPlayer == 'False' and args.addSchedule == 'True' and args.dbPassword:
		s = Singleton.getInstance()
		s.addScheduleToDb(scheduleFile,args.dbPassword)
	elif args.addPlayer == 'True' and args.addSchedule == 'False' and args.dbPassword:
		s = Singleton.getInstance()
		s.addPlayersToDb(playersFile,args.dbPassword)
	else:
		print("Not Proper Arguments Given")
	
if __name__ == '__main__':
	main()
