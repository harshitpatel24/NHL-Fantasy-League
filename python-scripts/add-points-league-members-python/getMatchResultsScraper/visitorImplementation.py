import abc
import database as dbImpl

class Visitor(abc.ABC,object):
	@abc.abstractmethod
	def visitLeagueMember(self,LeagueMember):
		pass
	@abc.abstractmethod
	def visitMemberTeam(self,MemberTeam):
		pass

class goalPointVisitor(Visitor):
	goalPoint = 3
	def __init__(self):
		pass
		
	def visitLeagueMember(self,LeagueMember,playerIdList,dbPassword):
		print("Visit League Member for adding Goal Points...")
		dbImplObj = dbImpl.Database()
		dbImplObj.addPointsToDb(dbPassword, 'leagueMember', playerIdList, self.goalPoint)

	def visitMemberTeam(self,MemberTeam,playerIdList,dbPassword):
		print("Visit Member Team for adding Goal Points...")
		dbImplObj = dbImpl.Database()
		dbImplObj.addPointsToDb(dbPassword,'memberTeam',playerIdList,self.goalPoint)
	
class assistPointVisitor(Visitor):
	assistPoint = 2
	def __init__(self):
		pass
		
	def visitLeagueMember(self,LeagueMember,playerIdList,dbPassword):
		print("Visit League Member for adding Assist Points...")
		dbImplObj = dbImpl.Database()
		dbImplObj.addPointsToDb(dbPassword, 'leagueMember', playerIdList, self.assistPoint)
	
	def visitMemberTeam(self,MemberTeam,playerIdList,dbPassword):
		print("Visit Member Team for adding Assist Points...")
		dbImplObj = dbImpl.Database()
		dbImplObj.addPointsToDb(dbPassword, 'memberTeam', playerIdList, self.assistPoint)

	
class Visitable(abc.ABC):
	@abc.abstractmethod
	def accept(self,Visitor,playerList,dbPassword):
		pass

class LeagueMember(Visitable):
	def __init__(self):
		pass
	
	def accept(self,Visitor,playerList,dbPassword):
		Visitor.visitLeagueMember(self,playerList,dbPassword)
	
class MemberTeam(Visitable):
	def __init__(self):
		pass
		
	def accept(self,Visitor,playerList,dbPassword):
		Visitor.visitMemberTeam(self,playerList,dbPassword)
