package org.wicketTutorial;
import java.util.Iterator;
import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.CheckedFolder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;


public class AutocheckedFolder<T> extends CheckedFolder<T> {

	private ITreeProvider<T> treeProvider;
	private Boolean nodeChecked;
	private IModel<Set<T>> checkedNodes;
	
	public AutocheckedFolder(String id, AbstractTree<T> tree, 
							 IModel<T> model, IModel<Set<T>> checkedNodes) {
		super(id, tree, model);	
		this.treeProvider = tree.getProvider();
		this.checkedNodes = checkedNodes;
		
		setOutputMarkupId(true);
	}
	
	@Override
	protected IModel<Boolean> newCheckBoxModel(IModel<T> model) {
		return new CheckModel(model.getObject());
	}
	
	@Override
	protected void onUpdate(AjaxRequestTarget target) {
		super.onUpdate(target);
		
		T node = getModelObject();
		addRemoveSubNodes(node, nodeChecked);				
		
		target.appendJavaScript(";$('#" + getMarkupId() + 
				"').closest('.tree-node').siblings('.tree-subtree')" +
				".find('input[type=checkbox]').prop('checked', " + nodeChecked + ");");
	}
	
	private void addRemoveSubNodes(T node, Boolean nodeStatus) {
		Iterator<? extends T> childrenNodes = treeProvider.getChildren(node);
		
		while (childrenNodes.hasNext()) {
			T childNode = childrenNodes.next();
			addRemoveSubNodes(childNode, nodeStatus);
		}
		
		if(nodeStatus)
			checkedNodes.getObject().add(node);
		else
			checkedNodes.getObject().remove(node);
	}
	
	class CheckModel implements IModel<Boolean>{		
		private T node;

		public CheckModel(T node){
			this.node = node;
		}
		
		@Override
		public void detach() {			
		}

		@Override
		public Boolean getObject() {
			return checkedNodes.getObject().contains(node);
		}

		@Override
		public void setObject(Boolean value) {						
			nodeChecked = value;
		}
		
	}
}
