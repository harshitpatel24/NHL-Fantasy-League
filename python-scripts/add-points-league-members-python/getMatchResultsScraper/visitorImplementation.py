import abc
class Visitor(abc.ABC,object):
	@abc.abstractmethod
	def visitLeagueMember(self,LeagueMember):
		pass
	@abc.abstractmethod
	def visitMemberTeam(self,MemberTeam):
		pass

class MemberVisitor(Visitor):
	def __init__(self):
		pass
		
	def visitLeagueMember(self,LeagueMember):
		print("in LeagueMember from LeagueMemberVisitor")
	
	def visitMemberTeam(self,MemberTeam):
		print("in memberTeam from LeagueMemberVisitor")
	

class Visitable(abc.ABC):
	@abc.abstractmethod
	def accept(self,Visitor):
		pass

class LeagueMember(Visitable):
	def __init__(self):
		pass
	
	def accept(self,Visitor):
		Visitor.visitLeagueMember(self)
	
class MemberTeam(Visitable):
	def __init__(self):
		pass
		
	def accept(self,Visitor):
		Visitor.visitMemberTeam(self)
