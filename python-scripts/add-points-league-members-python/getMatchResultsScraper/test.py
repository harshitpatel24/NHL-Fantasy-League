class A:
	def print1():
		print("a")
	
class B(A):
	def print2():
		super().print1()
		print("B")
		
b =B()