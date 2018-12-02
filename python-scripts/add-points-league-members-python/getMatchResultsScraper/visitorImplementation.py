import abc
import database as dbImpl

class Visitor(abc.ABC,object):
	@abc.abstractmethod
	def visitLeagueMember(self,LeagueMember):
		pass
	@abc.abstractmethod
	def visitHockeyPlayerStats(self,HockeyPlayerStats):
		pass

class goalPointVisitor(Visitor):
	goalPoint = 3
	def __init__(self):
		pass
		
	def visitLeagueMember(self,LeagueMember,playerIdList,dbPassword):
		print("Visit League Member for adding Goal Points...")
		dbImplObj = dbImpl.Database()
		dbImplObj.addPointsToDb(dbPassword, 'leagueMember', playerIdList, self.goalPoint,'G')

	def visitHockeyPlayerStats(self,HockeyPlayerStats,playerIdList,dbPassword):
		print("Visit hockeyPlayerStatsArchive for adding Goal Points...")
		dbImplObj = dbImpl.Database()
		dbImplObj.addPointsToDb(dbPassword,'hockeyPlayerStatsArchive',playerIdList,self.goalPoint,'G')
	
class assistPointVisitor(Visitor):
	assistPoint = 2
	def __init__(self):
		pass
		
	def visitLeagueMember(self,LeagueMember,playerIdList,dbPassword):
		print("Visit League Member for adding Assist Points...")
		dbImplObj = dbImpl.Database()
		dbImplObj.addPointsToDb(dbPassword, 'leagueMember', playerIdList, self.assistPoint,'A')
	
	def visitHockeyPlayerStats(self,HockeyPlayerStats,playerIdList,dbPassword):
		print("Visit hockeyPlayerStatsArchive for adding Assist Points...")
		dbImplObj = dbImpl.Database()
		dbImplObj.addPointsToDb(dbPassword, 'hockeyPlayerStatsArchive', playerIdList, self.assistPoint,'A')

	
class Visitable(abc.ABC):
	@abc.abstractmethod
	def accept(self,Visitor,playerList,dbPassword):
		pass

class LeagueMember(Visitable):
	def __init__(self):
		pass
	
	def accept(self,Visitor,playerList,dbPassword):
		Visitor.visitLeagueMember(self,playerList,dbPassword)
	
class HockeyPlayerStats(Visitable):
	def __init__(self):
		pass
		
	def accept(self,Visitor,playerList,dbPassword):
		Visitor.visitHockeyPlayerStats(self,playerList,dbPassword)
