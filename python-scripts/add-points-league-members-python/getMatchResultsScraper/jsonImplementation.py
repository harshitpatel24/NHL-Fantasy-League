import json
class Json:
	def loadJson(self,fileName):
			with open(fileName,'r') as players:
				jsonObjectList = json.load(players)
			return jsonObjectList

	def fetchScoreSummary(self,jsonObject):
		return jsonObject['scoreSummary']
		
	def fetchGoalScorrers(self,jsonObject):
		goalScorersList=[]
		scoreSummary = self.fetchScoreSummary(jsonObject)
		for teamName,teamGoalsSummary in scoreSummary.items():
			if teamGoalsSummary == []:
				pass
			else:
				for goalSummary in teamGoalsSummary:
					goalScorersList.append(goalSummary['scoredBy'])
		return goalScorersList

	def fetchAssistPlayers(self,jsonObject):
		assistPlayersList=[]
		scoreSummary = self.fetchScoreSummary(jsonObject)
		for teamName,teamGoalsSummary in scoreSummary.items():
			for goalSummary in teamGoalsSummary:
				if len(goalSummary['AssistBy']) == 0:
					pass
				else:
					for assistPlayer in goalSummary['AssistBy']:
						assistPlayersList.append(assistPlayer)
		return assistPlayersList
