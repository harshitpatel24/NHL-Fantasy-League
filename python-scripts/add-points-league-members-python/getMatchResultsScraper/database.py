import mysql.connector
class Database:
	def getConnection(self,dbPassword):
		try:
			connectionObj=mysql.connector.connect(user="root", password=dbPassword,host="localhost",database="NHLFantasyLeague")
			return connectionObj
		except:
			print("Database Connection Failed.....")
			return
	
	def getCursor(self,connectionObj):
		try:
			cursorObj = connectionObj.cursor(buffered=True)
			return cursorObj
		except:
			return None
	
	def searchPlayerId(self,connectionObj,playerName):
		cursorObj = self.getCursor(connectionObj)
		#print(cursorObj)
		if cursorObj != None:
			cursorObj.execute('select * from hockeyPlayer where name = "{0}"'.format(playerName))
			playerId = cursorObj.fetchone()
			cursorObj.close()
			return playerId
		else:
			cursorObj.close()
			return ()
	
	def getPlayerIdsFromDb(self,connectionObj,playersList):
		playerIds=[]
		for playerName in playersList:
			#print(playerName)
			playerId = self.searchPlayerId(connectionObj,playerName)
			if playerId == () or playerId == None:
				#print(playerName)
				pass
			else:
				playerIds.append(playerId[0])
		return playerIds