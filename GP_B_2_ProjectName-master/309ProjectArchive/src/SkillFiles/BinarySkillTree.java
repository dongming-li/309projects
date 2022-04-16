package SkillFiles;

import SkillFiles.Skill;

/*
 * Functional implementation for binary tree type used to organize
 * skill trees into easily traversible trees. This tree type is not
 * going to compare contents in order to determine heirarchy. Heirarchy
 * will be static and pre-defined
 * 
 * @author Anthony Bertucci
 */
public class BinarySkillTree
{
	private Skill data;
	private BinarySkillTree parentNode;
	private BinarySkillTree leftNode;
	private BinarySkillTree rightNode;
	private int treeSize;
	/*
	 * Constructor for the BinarySkillTree class.
	 * 
	 * @param Skill s skill to be used as the head/canopy of the new BinarySkillTree object.
	 */
	public BinarySkillTree(Skill s)
	{
		this.data = s;
		this.parentNode = null;
		this.leftNode = null;
		this.rightNode = null;
		treeSize = 1;
	}
	/*
	 * Returns the size of the given BinarySkillTree
	 * 
	 * @return size of BinarySkillTree
	 */
	public int getSize()
	{
		return treeSize;
	}
	/*
	 * Returns whether or not the given BinarySkillTree has a direct right tree node.
	 * 
	 * @param whether or not a right node exists.
	 */
	public boolean hasRightNode()
	{
		if (rightNode != null)
			return true;
		return false;
	}
	/*
	 * Returns whether or not the given BinarySkillTree has a direct left tree node. If false, the given BinarySkillTree is at the end of a branch.
	 * 
	 * @return whether or not a left node exists.
	 */
	public boolean hasLeftNode()
	{
		if (leftNode != null)
			return true;
		return false;
	}
	/*
	 * Adds a new node to the left or right of the given BinarySkillTree object.
	 * 
	 * @param skill to be located in the new node
	 * @param isRight whether or not the new node will be a left or right node of the given BinarySkillTree object.
	 */
	public void addNode(Skill s, boolean isRight)
	{
		if (isRight)
		{
			this.rightNode = new BinarySkillTree(s);
			rightNode.parentNode = this;
		}
		else
		{
			this.leftNode = new BinarySkillTree(s);
			leftNode.parentNode = this;
		}
		treeSize++;
	}
	/*
	 * Returns the skill held within the given BinarySkillTree canopy node.
	 * 
	 * @return Skill found within the node
	 */
	public Skill getData()
	{
		return data;
	}
	/*
	 * Deletes a given node and all of its children from the given BinarySkillTree
	 * 
	 * @param Node to be deleted.
	 */
	public void deleteNode(BinarySkillTree node)
	{
		//disconnect from children
		if (node.leftNode != null)
		{
			node.leftNode = null;
			treeSize -= node.leftNode.treeSize;
		}
		if (node.rightNode != null)
		{
			node.rightNode = null;
			treeSize -= node.leftNode.treeSize;
		}
		//if this node is a rightNode child
		if (node.parentNode.rightNode.data.equals(node.data))
		{
			node.parentNode.rightNode = null;
		}
		//if this node is a leftNode child
		else if (node.parentNode.leftNode.data.equals(node.data))
		{
			node.parentNode.leftNode = null;
		}
		
	}
	/*
	 * Returns left node of the given BinarySkillTree
	 * 
	 * @return Left node
	 */
	public BinarySkillTree getLeftChildSkillTree()
	{
		 return this.leftNode;
	}
	/*
	 * Returns right node of the given BinarySkillTree
	 * 
	 * @return Right node
	 */
	public BinarySkillTree getRightChildSkillTree()
	{
		return this.rightNode;
	}
	/*
	 * Returns parent node of the given BinarySkillTree. Useful for determining required skills
	 * 
	 * @return Parent node
	 */
	public BinarySkillTree getParentSkillTree()
	{
		if (!this.parentNode.equals(null))
			return this.parentNode;
		return null;
	}
	/*
	 * Recursively finds a skill (if it exists) within the given BinarySkillTree. Will return null if the given ID does not exist within the BinarySkillTree.
	 * 
	 * @return BinarySkillTree containing the found skill (if it is found)
	 */
	public BinarySkillTree findSkill(String skillID)
	{
		BinarySkillTree leftResult = new BinarySkillTree(new Skill("A", 1, false, "A"));
		BinarySkillTree rightResult = new BinarySkillTree(new Skill("A", 1, false, "A"));
		if (this.getData().getID().equals(skillID))
			return this;
		//has two children
		else if (this.rightNode != null)
		{
			rightResult = this.rightNode.findSkillAfterBranch(skillID);
			if (rightResult != null)
				return rightResult;
			else
			{
				leftResult = this.leftNode.findSkillAfterBranch(skillID);
				if (leftResult != null)
					return leftResult;
			}
		}
		//has only one child
		else if (this.leftNode != null)
		{
			leftResult = this.leftNode.findSkill(skillID);
			if (leftResult != null)
				return leftResult;
		}
		else
		{
			return null;
		}
		return null;
	}
	/*
	 * Recursive method used within findSkill to be used if the branch within the skill tree has already been passed (logically there is only one branched path
	 *  within a BinarySkillTree)
	 *  
	 *  @return BinarySkillTree where skillID was found (if it is found).
	 */
	public BinarySkillTree findSkillAfterBranch(String skillID)
	{
		BinarySkillTree result = new BinarySkillTree(new Skill("A", 1, false, "A"));
		if (this.getData().getID().equals(skillID))
		{
			return this;
		}
		else if (this.leftNode != null)
		{
			result = this.leftNode.findSkillAfterBranch(skillID);
			if (result != null)
				return result;
		}
		else
		{
			return null;
		}
		return null;
	}
}
