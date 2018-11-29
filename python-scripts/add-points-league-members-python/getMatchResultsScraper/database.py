import mysql.connector
import datetime
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
					cursorObj.execute("select teamId,memberId,playerId from memberTeam where playerId = {0}".format(playerId))
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

	def addPointsToHockeyPlayerStats(self,playerId,point,pointType,dbPassword):
		try:
			connectionObj = self.getConnection(dbPassword)
			try:
				cursorObj = self.getCursor(connectionObj)
				try:
					cursorObj.execute("select pointSummary,points,playerId from hockeyPlayerStatsArchive where playerId = {0} and date='{1}'".format(playerId,(datetime.datetime.now()-datetime.timedelta(1)).date()))
					hockeyPlayerStats = cursorObj.fetchone()
					if hockeyPlayerStats == None:
						cursorObj.execute("insert into hockeyPlayerStatsArchive (date,pointSummary,points,playerId) values ('{0}','{1}',{2},{3})".format((datetime.datetime.now()-datetime.timedelta(1)).date(),pointType,point,playerId))
						connectionObj.commit()
					else:
						cursorObj.execute("update hockeyPlayerStatsArchive set  pointSummary = '{0}',points = '{1}' where playerId = {2} and date = '{3}'".format(hockeyPlayerStats[0]+pointType,hockeyPlayerStats[1]+point,hockeyPlayerStats[2],(datetime.datetime.now()-datetime.timedelta(1)).date()))
						connectionObj.commit()

					#cursorObj.execute("update memberTeam set pointsEarned = {0} where playerId = {1} and memberId = {2}".format(memberTeam[1]+point,memberTeam[2],memberTeam[3]))
					#connectionObj.commit()
					#connectionObj.close()
				except:
					print("here")
			except:
				print("here1")
		except:
			print("here2")

	def addPointsToLeagueMembers(self,leagueMember,point,dbPassword):
		try:
			connectionObj = self.getConnection(dbPassword)
			try:
				cursorObj = self.getCursor(connectionObj)
				try:
					#print(leagueMember)
					cursorObj.execute("select points from leagueMember where id = {0}".format(int(leagueMember[1])))
					getExistingPoint = cursorObj.fetchone()
					cursorObj.execute("update leagueMember set points = {0} where id = {1}".format(getExistingPoint[0] + point,leagueMember[1]))
					connectionObj.commit()
				except:
					pass
			except:
				pass
		except:
			pass

	def getLeagueMembers(self,dbPassword):
		pass

	def addPointsToDb(self,dbPassword,tableName,playerIdList,point,pointType):
		if tableName == 'hockeyPlayerStatsArchive':
			for playerId in playerIdList:
				#print("player id",playerId)
				#print(playerId)
				#memberTeams = self.findTeamIdByPlayerId(playerId,dbPassword)
				self.addPointsToHockeyPlayerStats(playerId,point,pointType,dbPassword)


		elif tableName == 'leagueMember':
			#leagueMembers = self.getLeagueMembers(dbPassword)
			for playerId in playerIdList:
				leagueMembers = self.findTeamIdByPlayerId(playerId, dbPassword)
				#print(leagueMembers)
				for leagueMember in leagueMembers:
					self.addPointsToLeagueMembers(leagueMember, point, dbPassword)
				#leagueMembers = self.findTeamIdByPlayerId(playerId, dbPassword)
				#self.addPointsToMembers(memberTeams, point, dbPassword)
				#print('playerId',playerId)
			#print("from leagueMember for adding Goals")
			#print(playerIdList)
		else:
			pass

	def getAllMemberIds(self,connectionObj):
		memberIds=[]
		try:
			cursorObj = self.getCursor(connectionObj)
			try:
				cursorObj.execute("select distinct(memberId) from memberTeam")
				members=cursorObj.fetchall()
				for member in members:
					memberIds.append(member[0])
				connectionObj.commit()
				cursorObj.close()
			except:
				pass
		except:
			pass
		return memberIds

	def addMemberPlayerToArchiveDb(self,memberPlayer,connectionObj):
		try:
			cursorObj = self.getCursor(connectionObj)
			try:
				cursorObj.execute("insert into memberTeamArchive (date,playerId,memberId) values ('{0}',{1},{2})".format(datetime.datetime.now() - datetime.timedelta(1),memberPlayer[1],memberPlayer[0]))
				connectionObj.commit()
			except:
				pass
		except:
			pass

	def saveAllMemberTeamToArchive(self,memberIds,connectionObj):
		try:
			cursorObj = self.getCursor(connectionObj)
			try:
				for memberId in memberIds:
					cursorObj.execute("select memberId,playerId from memberTeam where memberId = {0}".format(memberId))
					memberTeam = cursorObj.fetchall()
					for memberPlayer in memberTeam:
						self.addMemberPlayerToArchiveDb(memberPlayer,connectionObj)
			except:
				pass
		except:
			pass
		return memberIds



	def saveMemberTeamToArchive(self,connectionObj):
		memberIds = self.getAllMemberIds(connectionObj)
		self.saveAllMemberTeamToArchive(memberIds,connectionObj)
