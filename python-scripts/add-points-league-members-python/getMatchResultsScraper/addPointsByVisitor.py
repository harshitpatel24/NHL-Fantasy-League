from datetime import datetime
import visitorImplementation as visitorImpl
import jsonImplementation as jsonImpl
import database as dbImpl
import argparse

def main():
	
	#fetching arguments
	parser = argparse.ArgumentParser(description="Points added to db")
	parser.add_argument("--dbPassword", help="database password", required=True)
	args = parser.parse_args()
	dbPassword = args.dbPassword
	
	
	#creating objects
	jsonImplObj = jsonImpl.Json()
	dbImplObj = dbImpl.Database()
	goalPointVisitorObj = visitorImpl.goalPointVisitor()
	assistPointVisitorObj = visitorImpl.assistPointVisitor()
	leagueMemberObj = visitorImpl.LeagueMember()
	hockeyPlayerStatsObj = visitorImpl.HockeyPlayerStats()
	
	#reading file with match results
	fileName=datetime.now().strftime("%Y-%m-%d") + '.json'
	
	#fetching goalScorrer and assistPlayers data from json file
	jsonObjecList = jsonImplObj.loadJson(fileName)
	goalScorersList=[]
	assistPlayersList=[]
	for jsonObject in jsonObjecList:
		for goalScorrer in jsonImplObj.fetchGoalScorrers(jsonObject):
			goalScorersList.append(goalScorrer)
		for assistPlayer in jsonImplObj.fetchAssistPlayers(jsonObject):
			assistPlayersList.append(assistPlayer)




	#getting playerIDs from DBs
	connectionObj = dbImplObj.getConnection(dbPassword)
	goalScorrersIdList = dbImplObj.getPlayerIdsFromDb(connectionObj,goalScorersList)
	assistPlayersIdsList = dbImplObj.getPlayerIdsFromDb(connectionObj,assistPlayersList)
	connectionObj.close()

	connectionObj = dbImplObj.getConnection(dbPassword)
	dbImplObj.saveMemberTeamToArchive(connectionObj)
	connectionObj.close()

	#calling accept of leagueMemberObj and memberTeamObj to visit each leagueMember and memberTeam to add Goal points
	hockeyPlayerStatsObj.accept(goalPointVisitorObj, goalScorrersIdList, dbPassword)
	leagueMemberObj.accept(goalPointVisitorObj,goalScorrersIdList,dbPassword)

	
	#calling accept of leagueMemberObj and memberTeamObj to visit each leagueMember and memberTeam to add Assist points
	hockeyPlayerStatsObj.accept(assistPointVisitorObj, assistPlayersIdsList, dbPassword)
	leagueMemberObj.accept(assistPointVisitorObj,assistPlayersIdsList,dbPassword)

	
	
if __name__ == '__main__':
	main()