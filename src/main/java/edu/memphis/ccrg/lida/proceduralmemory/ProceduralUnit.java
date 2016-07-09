package edu.memphis.ccrg.lida.proceduralmemory;

import edu.memphis.ccrg.lida.actionselection.Action;
import edu.memphis.ccrg.lida.actionselection.Behavior;
import edu.memphis.ccrg.lida.framework.shared.ElementFactory;
import edu.memphis.ccrg.lida.framework.shared.activation.Activatible;

import java.util.Collection;

/**
 * An abstraction of the commonality between {@link Scheme} and {@link Behavior}
 * .
 * 
 * @author Ryan J. McCall
 * @author Javier Snaider
 */
public interface ProceduralUnit extends Activatible {

	/**
	 * Gets action.
	 * 
	 * @return the {@link Action} this unit contains
	 */
    Action getAction();

	/**
	 * Gets context conditions.
	 * 
	 * @return the context's conditions
	 */
    Collection<Condition> getContextConditions();

	/**
	 * Gets adding list.
	 * 
	 * @return the adding list
	 */
    Collection<Condition> getAddingList();

	/**
	 * Gets deleting list.
	 * 
	 * @return the deleting list
	 */
    Collection<Condition> getDeletingList();

	/**
	 * Gets the label.
	 * 
	 * @return label of the unit
	 */
    String getLabel();

	/**
	 * Sets Scheme's label
	 * 
	 * @param l
	 *            a String
	 */
    void setLabel(String l);

	/**
	 * Sets unique identifier for {@link Scheme}. Should be used by
	 * {@link ElementFactory} only.
	 * 
	 * @param id
	 *            unique identifier for this scheme
	 */
    void setId(int id);

	/**
	 * Gets scheme's id.
	 * 
	 * @return unique identifier for this scheme
	 */
    int getId();
}