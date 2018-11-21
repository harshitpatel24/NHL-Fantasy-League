import mysql.connector
import json
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
			conn=mysql.connector.connect(user='root', password='harshit24',host='localhost',database='NHLFantasyLeague')
			return conn
		except:
			print("Database Connection Failed.....")
			return
		
	def loadJson(self,fileName):
		with open('final758PlayersData.json','r') as players:
			jsonObjectList = json.load(players)
		return jsonObjectList
	
	def insretInToDb(self,connection,jsonObject):
		try:
			cursor = connection.cursor()
			try:
				cursor.execute("INSERT INTO hockeyPlayer(name,teamName,teamAbbr,playerRank,position,tShirtNo,age,height,weight,birthday) VALUES ('{0}','{1}','{2}',{3},'{4}',{5},{6},'{7}',{8},'{9}')".format(jsonObject['playerName'],jsonObject['teamName'],jsonObject['teamAbbrevation'],jsonObject['rank'],jsonObject['position'],jsonObject['tShirtNumber'],jsonObject['age'],jsonObject['height'],jsonObject['weight'],jsonObject['birthDate']))
			except:
				print('Error during Inserting')
			connection.commit()
			cursor.close()
		except:
			print("Unable to create cursor")
	
	def saveJsonObjToDb(self,jsonObject):
		connection = self.getConnection()
		self.insretInToDb(connection,jsonObject)
		connection.close()
	
	def iterateAndSaveInDb(self,jsonObjectList):
		for jsonObject in jsonObjectList:
			self.saveJsonObjToDb(jsonObject)
	
	def addToDb(self):
		jsonObjectList = self.loadJson('final758PlayersData.json')
		#print(jsonObjectList)
		#print(len(jsonObjectList))
		self.iterateAndSaveInDb(jsonObjectList)
def main():
	s = Singleton.getInstance()
	s.addToDb()

if __name__ == '__main__':
	main()