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

	def addPointsToMemberTeams(self,memberTeams,point,dbPassword):
		for memberTeam in memberTeams:
			try:
				connectionObj = self.getConnection(dbPassword)
				try:
					cursorObj = self.getCursor(connectionObj)
					try:
						cursorObj.execute("update memberTeam set pointsEarned = {0} where playerId = {1} and memberId = {2}".format(memberTeam[1]+point,memberTeam[2],memberTeam[3]))
						connectionObj.commit()
						connectionObj.close()
					except:
						pass
				except:
					pass
			except:
				pass

	def addPointsToLeagueMembers(self,leagueMember,point,dbPassword):
		try:
			connectionObj = self.getConnection(dbPassword)
			try:
				cursorObj = self.getCursor(connectionObj)
				try:

					cursorObj.execute("select * from leagueMember where id = {0}".format(int(leagueMember[3])))
					getExistingPoint = cursorObj.fetchone()
					cursorObj.execute("update leagueMember set points = {0} where id = {1}".format(getExistingPoint[2] + point,leagueMember[3]))
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
			for playerId in playerIdList:
				#print("player id",playerId)
				memberTeams = self.findTeamIdByPlayerId(playerId,dbPassword)
				self.addPointsToMemberTeams(memberTeams,point,dbPassword)


		elif tableName == 'leagueMember':
			for playerId in playerIdList:
				leagueMembers = self.findTeamIdByPlayerId(playerId, dbPassword)
				for leagueMember in leagueMembers:
					self.addPointsToLeagueMembers(leagueMember, point, dbPassword)
				#leagueMembers = self.findTeamIdByPlayerId(playerId, dbPassword)
				#self.addPointsToMembers(memberTeams, point, dbPassword)
				#print('playerId',playerId)
			#print("from leagueMember for adding Goals")
			#print(playerIdList)

		else:
			pass


			