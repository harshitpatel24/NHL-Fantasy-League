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

	def findTeamIdByPlayerId(self,playerId,dbPassword):
		try:
			connectionObj = self.getConnection(dbPassword)
			try:
				cursorObj = self.getCursor(connectionObj)
				try:
					cursorObj.execute("select * from memberTeam where playerId = {0}".format(playerId))
					memberTeams = cursorObj.fetchall()
					cursorObj.close()
					connectionObj.close()
					return memberTeams
				except:
					return ()
			except:
				return ()
		except:
			return ()

	def addPointsToMembers(self,memberTeams,goalPoint,dbPassword):
		for memberTeam in memberTeams:
			print(memberTeam[2],memberTeam[3])
			try:
				connectionObj = self.getConnection(dbPassword)
				try:
					cursorObj = self.getCursor(connectionObj)
					try:
						cursorObj.execute("update memberTeam set pointsEarned = {0} where playerId = {1} and memberId = {2}".format(memberTeam[1]+goalPoint,memberTeam[2],memberTeam[3]))
						connectionObj.commit()
						connectionObj.close()
					except:
						pass
				except:
					pass
			except:
				pass



	def addPointsToDb(self,dbPassword,tableName,playerIdList,point):
		if tableName == 'memberTeam':
			print("from memberTeam for adding Goals")
			for playerId in playerIdList:
				print("player id",playerId)
				memberTeams = self.findTeamIdByPlayerId(playerId,dbPassword)
				self.addPointsToMembers(memberTeams,point,dbPassword)


		elif tableName == 'leagueMember':
			#print("from leagueMember for adding Goals")
			#print(playerIdList)
			pass
		else:
			pass


			