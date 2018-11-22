from __future__ import print_function
import mysql.connector
import json
import sys
import datetime
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

	def getConnection(self):
		try:
			conn=mysql.connector.connect(user="root", password="harshit24",host="localhost",database="NHLFantasyLeague")
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

	def insretInToDb(self,connection,jsonObject,table):
		try:
			cursor = connection.cursor()
			if table == 'Player':
				if not self.checkAlreadyExists(jsonObject,table,cursor):
					try:
						cursor.execute("INSERT INTO hockeyPlayer(name,teamName,teamAbbr,playerRank,position,tShirtNo,age,height,weight,birthday) VALUES ('{0}','{1}','{2}',{3},'{4}',{5},{6},'{7}',{8},'{9}')".format(jsonObject['playerName'],jsonObject['teamName'],jsonObject['teamAbbrevation'],jsonObject['rank'],jsonObject['position'],jsonObject['tShirtNumber'],jsonObject['age'],jsonObject['height'],jsonObject['weight'],jsonObject['birthDate']))
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

	def saveJsonObjToHockeyPlayerTable(self,jsonObject):
		connection = self.getConnection()
		self.insretInToDb(connection,jsonObject,'Player')
		connection.close()
	
	def saveJsonObjToScheduleTable(self,jsonObject):
		connection = self.getConnection()
		self.insretInToDb(connection,jsonObject,'Schedule')
		connection.close()

	def iterateAndSavePlayerInDb(self,jsonObjectList):
		for jsonObject in jsonObjectList:
			self.saveJsonObjToHockeyPlayerTable(jsonObject)
	
	def iterateAndSaveScheduleInDb(self,jsonObjectList):
		for jsonObject in jsonObjectList:
			self.saveJsonObjToScheduleTable(jsonObject)

	def addPlayersToDb(self,fileName):
		jsonObjectList = self.loadJson(fileName)
		#print(jsonObjectList)
		#print(len(jsonObjectList))
		self.iterateAndSavePlayerInDb(jsonObjectList)
	
	def addScheduleToDb(self,fileName):
		jsonObjectList = self.loadJson(fileName)
		self.iterateAndSaveScheduleInDb(jsonObjectList)

def main():
	playersFile='final757PlayersData.json'
	scheduleFile='NHLFullSchedule.json'
	args=sys.argv
	if len(args) == 1:
		print("Exiting arguments required...")
	else:
		s = Singleton.getInstance()
		if len(args) == 2:
			if args[1] == 'addPlayers':
				s.addPlayersToDb(playersFile)
			elif args[1] == 'addSchedule':
				s.addScheduleToDb(scheduleFile)
			else:
				print("Wrong Argument")
		elif len(args) == 3:
			if args[1] == 'addPlayers':
				if args[2] == 'addSchedule':
					s.addPlayersToDb(playersFile)
					s.addScheduleToDb(scheduleFile)
				else:
					print("wrong arguments")
			elif args[1] == 'addSchedule':
				if args[2] == 'addPlayers':
					s.addPlayersToDb(playersFile)
					s.addScheduleToDb(scheduleFile)
				else:
					print("wrong arguments")
			else:
				print("wrong arguments")
		else:
			print("If you want to extend write code here")

if __name__ == '__main__':
	main()
