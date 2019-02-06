package ssii.p2;

import java.util.List;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.GoalTest;
import aima.core.search.framework.Problem;
import aima.core.search.framework.QueueSearch;
import aima.core.search.framework.ResultFunction;
import aima.core.search.framework.TreeSearch;
import aima.core.search.informed.AStarSearch;
import ssii.p1.Utils;
import ssii.p1.actions.Location;
import ssii.p1.actions.VacuumActionsFunction;
import ssii.p1.actions.VacuumResultFunction;
import ssii.p1.agent.VacuumGoalTest;
import ssii.p1.state.VacuumState;

public class EjemploAStarHGlobalDirt {
	

	public List<Action> getActions(VacuumState initialState) throws Exception{
		QueueSearch qs=new TreeSearch();
		aima.core.search.framework.HeuristicFunction hf=new 
				aima.core.search.framework.HeuristicFunction(){
			@Override
			public double h(Object state) {
				VacuumState vs=(VacuumState)state;						 
				return vs.getGlobalDirtCount();
			}

		};
		AStarSearch bfs=new aima.core.search.informed.AStarSearch(qs,hf);   	    	

		ActionsFunction actionsFunction = new VacuumActionsFunction();

		ResultFunction resultFunction = new VacuumResultFunction();

		GoalTest goalTest = new VacuumGoalTest();

		Problem vacuumProblem=new Problem(initialState,actionsFunction,resultFunction,goalTest);
		List<Action> actions = bfs.search(vacuumProblem);    

		return actions;
	}
	
	public static void main(String args[]) throws Exception{
		EjemploAStarHGlobalDirt dfst=new EjemploAStarHGlobalDirt();
		int[][] world=new int[][]{
				new int[]{0,0,0},
				new int[]{0,0,0},
				new int[]{0,5,0}};
		VacuumState initialState = new VacuumState(new Location(0,0), 
				world);
		List<Action> actions = dfst.getActions(initialState);
		System.out.println("solution:" + actions);      
		Utils.animate(actions,initialState);

	}
}