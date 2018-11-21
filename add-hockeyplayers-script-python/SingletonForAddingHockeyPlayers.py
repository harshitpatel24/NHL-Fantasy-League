#from __future__ import print_function
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

	def insretInToDb(self,connection,jsonObject,table):
		try:
			cursor = connection.cursor()
			if table == 'Player':
				try:
					cursor.execute("INSERT INTO hockeyPlayer(name,teamName,teamAbbr,playerRank,position,tShirtNo,age,height,weight,birthday) VALUES ('{0}','{1}','{2}',{3},'{4}',{5},{6},'{7}',{8},'{9}')".format(jsonObject['playerName'],jsonObject['teamName'],jsonObject['teamAbbrevation'],jsonObject['rank'],jsonObject['position'],jsonObject['tShirtNumber'],jsonObject['age'],jsonObject['height'],jsonObject['weight'],jsonObject['birthDate']))
				except:
					print('Error during Inserting Player')
				connection.commit()
			elif table == 'Schedule':
				try:
					cursor.execute("INSERT INTO matchSchedule(team1,team2,date) VALUES ('{0}','{1}','{2}')".format(jsonObject['team1'],jsonObject['team2'],datetime.datetime.strptime(jsonObject['date'],"%Y-%m-%d").date()))
					connection.commit()
				except:
					print('Error during Inserting Schedule')
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
	
	args=sys.argv
	if len(args) == 1:
		print("Exiting")
	else:
		s = Singleton.getInstance()
		if len(args) == 2:
			if args[1] == 'addPlayers':
				s.addPlayersToDb('final758PlayersData.json')
			elif args[1] == 'addSchedule':
				s.addScheduleToDb('NHLFullSchedule.json')
			else:
				print("Wrong Argument")
		elif len(args) == 3:
			print("If you want to extend write code here")
	

if __name__ == '__main__':
	main()
