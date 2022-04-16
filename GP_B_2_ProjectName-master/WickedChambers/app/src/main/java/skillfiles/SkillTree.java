package skillfiles;

import android.widget.Toast;

import skillfiles.Skill;

/**
 * Functional implementation for binary tree type used to organize
 * skill trees into easily traversible trees. This tree type is not
 * going to compare contents in order to determine heirarchy. Heirarchy
 * will be static and pre-defined
 *
 * @author Anthony Bertucci
 */
public class SkillTree
{
    private Skill data;
    private SkillTree parentNode;
    private SkillTree leftNode;
    private SkillTree rightNode;
    private int treeSize;
    private int numUnlocked;
    private char type;
    private String skillIdOfLatestSkill;
    private int position;
    /**
     * Constructor for the BinarySkillTree class.
     *
     * @param s skill to be used as the head/canopy of the new BinarySkillTree object.
     */
    public SkillTree(Skill s)
    {
        this.data = s;
        this.parentNode = null;
        this.leftNode = null;
        this.rightNode = null;
        treeSize = 1;
        numUnlocked = 1;
    }
    /**
     * Returns the size of the given BinarySkillTree
     *
     * @return size of BinarySkillTree
     */
    public int getSize()
    {
        return treeSize;
    }
    /**
    *   Returns the number of unlocked skills in the given SkillTree
    *
     */
    public int getNumUnlocked()
    {
        return numUnlocked;
    }

    /**
     * Returns whether or not the given BinarySkillTree has a direct right tree node.
     *
     * @return whether or not a right node exists.
     */
    public boolean hasRightNode()
    {
        if (rightNode != null)
            return true;
        return false;
    }
    /**
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
    /**
     * Adds a new node to the left or right of the given BinarySkillTree object.
     *
     * @param s Skill to be located in the new node
     * @param isRight whether or not the new node will be a left or right node of the given BinarySkillTree object.
     */
    public void addNode(Skill s, boolean isRight)
    {
        if (isRight)
        {
            this.rightNode = new SkillTree(s);
            rightNode.parentNode = this;
        }
        else
        {
            this.leftNode = new SkillTree(s);
            leftNode.parentNode = this;
        }
        treeSize++;
    }
    /**
     * Returns the skill held within the given BinarySkillTree canopy node.
     *
     * @return Skill found within the node
     */
    public Skill getData()
    {
        return data;
    }
    /**
     * Deletes a given node and all of its children from the given BinarySkillTree
     *
     * @param node Node to be deleted.
     */
    public void deleteNode(SkillTree node)
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
    /**
     * Returns left node of the given BinarySkillTree
     *
     * @return Left node
     */
    public SkillTree getLeftChildSkillTree()
    {
        return this.leftNode;
    }
    /**
     * Returns right node of the given BinarySkillTree
     *
     * @return Right node
     */
    public SkillTree getRightChildSkillTree()
    {
        return this.rightNode;
    }
    /**
     * Returns parent node of the given BinarySkillTree. Useful for determining required skills
     *
     * @return Parent node
     */
    public SkillTree getParentSkillTree()
    {
        if (!this.parentNode.equals(null))
            return this.parentNode;
        return null;
    }
    /**
     * Recursively finds a skill (if it exists) within the given BinarySkillTree. Will return null if the given ID does not exist within the BinarySkillTree.
     *
     * @return BinarySkillTree containing the found skill (if it is found)
     */
    public SkillTree findSkill(String skillID)
    {
        SkillTree leftResult = new SkillTree(new Skill("A", 1, false, "A", 0, 0, 0, 0));
        SkillTree rightResult = new SkillTree(new Skill("A", 1, false, "A", 0, 0, 0, 0));
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
    /**
     * Recursive method used within findSkill to be used if the branch within the skill tree has already been passed (logically there is only one branched path
     *  within a BinarySkillTree)
     *
     *  @return BinarySkillTree where skillID was found (if it is found).
     */
    public SkillTree findSkillAfterBranch(String skillID)
    {
        SkillTree result = new SkillTree(new Skill("A", 1, false, "A", 0, 0, 0, 0));
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


    /**
     * Generates a skill tree for the Warrior class. The tree hierarchy is as
     * follows: WR_00 | WR_01 | WR_02---| | | WR_03 WR_06 | | WR_04 WR_07 | |
     * WR_05 WR_08
     *
     * @return warrior skill tree
     */
    public static SkillTree generateWarriorSkillTree() {

        SkillTree warriorTree = new SkillTree(new Skill("WR_00", 0, true, "Path of the Warrior: +10 Strength, +5 Health", 5, 10, 0, 0));
        // nodes will be leftNodes unless there are two nodes necessary
        // (branches)
        warriorTree.addNode(new Skill("WR_01", 1, false, "Path of the Warrior 2: +15 Strength, +10 Health", 10, 15, 0, 0), false);
        warriorTree.getLeftChildSkillTree().addNode(new Skill("WR_02", 2, false, "Path of the Warrior 3: +5 Strength, +5 Health", 5, 5, 0, 0), false);
        warriorTree.getLeftChildSkillTree().getLeftChildSkillTree().addNode(new Skill("WR_03", 3, false, "Path of the Paladin: +15 Health", 15, 0, 0, 0), false);
        warriorTree.getLeftChildSkillTree().getLeftChildSkillTree().addNode(new Skill("WR_06", 6, false, "Path of the Berserker: +15 Strength", 0, 15, 0, 0), true); // creates
        // first and only branch
        warriorTree.getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().addNode(new Skill("WR_04", 4, false, "Path of the Paladin 2: +10 Health", 10, 0, 0, 0), false);
        warriorTree.getLeftChildSkillTree().getLeftChildSkillTree().getRightChildSkillTree().addNode(new Skill("WR_07", 7, false, "Path of the Berserker 2: +15 Strength", 0, 15, 0, 0), false);
        warriorTree.getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().addNode(new Skill("WR_05", 5, false, "Path of the Paladin 3: +20 Health", 20, 0, 0 ,0), false);
        warriorTree.getLeftChildSkillTree().getLeftChildSkillTree().getRightChildSkillTree().getLeftChildSkillTree().addNode(new Skill("WR_08", 8, false, "Path of the Berserker 3: +15 Strength", 0, 15, 0, 0), false);
        warriorTree.type = 'w';
        warriorTree.skillIdOfLatestSkill = "WR_00";
        return warriorTree;
    }

    /**
     * Generates a skill tree for the Mage class. The tree hierarchy is as
     * follows: MG_00 | MG_01 | MG_02---| | | MG_03 MG_06 | | MG_04 MG_07 | |
     * MG_05 MG_08
     *
     * @return mage skill tree
     */
    public static SkillTree generateMageSkillTree() {
        SkillTree mageTree = new SkillTree(new Skill("MG_00", 0, true, "Path of the Mage: +10 Intellect", 0, 0, 10, 0));
        // nodes will be leftNodes unless there are two nodes necessary
        // (branches)
        mageTree.addNode(new Skill("MG_01", 1, false, "Path of the Mage 2: +15 Intellect", 0, 0, 15, 0), false);
        mageTree.getLeftChildSkillTree().addNode(new Skill("MG_02", 2, false, "Path of the Mage 3: +20 Intellect", 0, 0, 20, 0), false);
        mageTree.getLeftChildSkillTree().getLeftChildSkillTree().addNode(new Skill("MG_03", 3, false, "Path of the Warlock: +25 Intellect, -20 Health", -20, 0, 25, 0), false);
        mageTree.getLeftChildSkillTree().getLeftChildSkillTree().addNode(new Skill("MG_06", 6, false, "Path of the Healer: +5 Health", 5, 0, 0, 0), true); // creates
        // first and only branch
        mageTree.getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().addNode(new Skill("MG_04", 4, false, "Path of the Warlock 2: +30 Intellect", 0, 0, 30, 0), false);
        mageTree.getLeftChildSkillTree().getLeftChildSkillTree().getRightChildSkillTree().addNode(new Skill("MG_07", 7, false, "Path of the Healer 2: +10 Health", 10, 0, 0, 0), false);
        mageTree.getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().addNode(new Skill("MG_05", 5, false, "Path of the Warlock 3: +50 Intellect, -10 Health", -10, 0, 50, 0), false);
        mageTree.getLeftChildSkillTree().getLeftChildSkillTree().getRightChildSkillTree().getLeftChildSkillTree().addNode(new Skill("MG_08", 8, false, "Path of the Healer 3: +10 Health, +5 Intellect", 10, 0, 5, 0), false);
        mageTree.type = 'm';
        mageTree.skillIdOfLatestSkill = "MG_00";
        return mageTree;
    }

    /**
     * Generates a skill tree for the Rogue class. The tree hierarchy is as
     * follows: RG_00 | RG_01 | RG_02---| | | RG_03 RG_06 | | RG_04 RG_07 | |
     * RG_05 RG_08
     *
     * @return rogue skill tree
     */
    public static SkillTree generateRogueSkillTree() {
        SkillTree rogueTree = new SkillTree(new Skill("RG_00", 0, true, "Path of the Rogue: +10 Agility", 0, 0, 0, 10));
        // nodes will be leftNodes unless there are two nodes necessary
        // (branches)
        rogueTree.addNode(new Skill("RG_01", 1, false, "Path of the Rogue 2: +10 Agility, +5 Health", 5, 0, 0, 10), false);
        rogueTree.getLeftChildSkillTree().addNode(new Skill("RG_02", 2, false, "Path of the Rogue 3: +15 Agility", 0, 0, 0, 15), false);
        rogueTree.getLeftChildSkillTree().getLeftChildSkillTree().addNode(new Skill("RG_03", 3, false, "Path of the Bowman: +15 Agility, -20 Health", -20, 0, 0, 15), false);
        rogueTree.getLeftChildSkillTree().getLeftChildSkillTree().addNode(new Skill("RG_06", 6, false, "Path of the Assassin: +15 Agility, -20 Health", -20, 0, 0, 15), true); // creates first and only branch
        rogueTree.getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().addNode(new Skill("RG_04", 4, false, "Path of the Bowman 2: +20 Agility", 0, 0, 0, 20), false);
        rogueTree.getLeftChildSkillTree().getLeftChildSkillTree().getRightChildSkillTree().addNode(new Skill("RG_07", 7, false, "Path of the Assassin 2: +20 Agility, -5 Health", -5, 0, 0, 20), false);
        rogueTree.getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().addNode(new Skill("RG_05", 5, false, "Path of the Bowman 3: +20 Agility", 0, 0, 0, 20), false);
        rogueTree.getLeftChildSkillTree().getLeftChildSkillTree().getRightChildSkillTree().getLeftChildSkillTree().addNode(new Skill("RG_08", 8, false, "Path of the Assassin 3: +10 Agility", 0, 0, 0, 10), false);
        rogueTree.type = 'r';
        rogueTree.skillIdOfLatestSkill = "RG_00";
        return rogueTree;
    }

    /**
     * Unlocks the corresponding node within a SkillTree for the skillID provided
     * @param skillID ID of skill to be unlocked
     * @return whether or not the skill was unlocked
     */
    public boolean unlockNode(String skillID)
    {
        //skill was found
        if (findSkill(skillID) != null)
        {
            SkillTree foundSkill = findSkill(skillID);
            //if skill is already unlocked
            if (foundSkill.getData().getUnlockedValue() || foundSkill.getParentSkillTree().getData().getUnlockedValue() != true)
            {
                return false;
            }
            //if skill is not unlocked
            foundSkill.getData().skillIsUnlocked = true;
            numUnlocked++;
            skillIdOfLatestSkill = foundSkill.getData().getID();
            return true;
        }
        return false;
    }

    /**
     * Unlocks the given SkillTree at its head
     * @return whether or not the skill was unlocked
     */
    public boolean unlock()
    {
        this.getData().unlock();
        return true;
    }

    /**
     * Converts the given SkillTree into a String for the purposes of database management. Since there are three possible classes, and only 8 states in which a skill tree can
     * exist, a SkillTree serialization is organized in the following way:
     *
     * class z state
     * @return
     */
    public String serialize()
    {
        String output = "";
        if (this.type == 'w')
            output += "wz";
        else if (this.type == 'm')
            output += "mz";
        else if (this.type == 'r')
            output += "rz";
        else
            output += "nz";
        if (skillIdOfLatestSkill.contains("00"))
        {
            output += "0";
        }
        else if (skillIdOfLatestSkill.contains("01"))
        {
            output += "1";
        }
        else if (skillIdOfLatestSkill.contains("02"))
        {
            output += "2";
        }
        else if (skillIdOfLatestSkill.contains("03"))
        {
            output += "3";
        }
        else if (skillIdOfLatestSkill.contains("04"))
        {
            output += "4";
        }
        else if (skillIdOfLatestSkill.contains("05"))
        {
            output += "5";
        }
        else if (skillIdOfLatestSkill.contains("06"))
        {
            output += "6";
        }
        else if (skillIdOfLatestSkill.contains("07"))
        {
            output += "7";
        }
        else if (skillIdOfLatestSkill.contains("08"))
        {
            output += "8";
        }
        return output;
    }

    /**
     * Creates a SkillTree object based on the String value read in from the database. To be used in tandem with serialize() to succesfully push and pull a SkillTree object from
     * the database
     * @param input String desired to be deserialized
     * @return SkillTree resulting from the String input
     */
    public static SkillTree deserialize(String input)
    {
        SkillTree output = null;
        String[] contents = input.split("z");
        if (contents[0].equals("w"))
            output = generateWarriorSkillTree();
        if (contents[0].equals("m"))
            output = generateMageSkillTree();
        if (contents[0].equals("r"))
            output = generateRogueSkillTree();
        int value = Integer.parseInt(contents[1]);
        if (value == 1)
        {
            output.getLeftChildSkillTree().unlock();
            output.unlock();
            if (contents[0].equals("w"))
            {
                output.skillIdOfLatestSkill = "WR_01";
            }
            else if (contents[0].equals("m"))
            {
                output.skillIdOfLatestSkill = "MG_01";
            }
            else if (contents[0].equals("r"))
            {
                output.skillIdOfLatestSkill = "RG_01";
            }
        }
        else if (value == 2)
        {
            output.getLeftChildSkillTree().getLeftChildSkillTree().unlock();
            output.getLeftChildSkillTree().unlock();
            output.unlock();
            if (contents[0].equals("w"))
            {
                output.skillIdOfLatestSkill = "WR_02";
            }
            else if (contents[0].equals("m"))
            {
                output.skillIdOfLatestSkill = "MG_02";
            }
            else if (contents[0].equals("r"))
            {
                output.skillIdOfLatestSkill = "RG_02";
            }
        }
        else if (value == 3)
        {
            output.getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().unlock();
            output.getLeftChildSkillTree().getLeftChildSkillTree().unlock();
            output.getLeftChildSkillTree().unlock();
            output.unlock();
            if (contents[0].equals("w"))
            {
                output.skillIdOfLatestSkill = "WR_03";
            }
            else if (contents[0].equals("m"))
            {
                output.skillIdOfLatestSkill = "MG_03";
            }
            else if (contents[0].equals("r"))
            {
                output.skillIdOfLatestSkill = "RG_03";
            }
        }
        else if (value == 4)
        {
            output.getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().unlock();
            output.getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().unlock();
            output.getLeftChildSkillTree().getLeftChildSkillTree().unlock();
            output.getLeftChildSkillTree().unlock();
            output.unlock();
            if (contents[0].equals("w"))
            {
                output.skillIdOfLatestSkill = "WR_04";
            }
            else if (contents[0].equals("m"))
            {
                output.skillIdOfLatestSkill = "MG_04";
            }
            else if (contents[0].equals("r"))
            {
                output.skillIdOfLatestSkill = "RG_04";
            }
        }
        else if (value == 5)
        {
            output.getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().unlock();
            output.getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().unlock();
            output.getLeftChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().unlock();
            output.getLeftChildSkillTree().getLeftChildSkillTree().unlock();
            output.getLeftChildSkillTree().unlock();
            output.unlock();
            if (contents[0].equals("w"))
            {
                output.skillIdOfLatestSkill = "WR_05";
            }
            else if (contents[0].equals("m"))
            {
                output.skillIdOfLatestSkill = "MG_05";
            }
            else if (contents[0].equals("r"))
            {
                output.skillIdOfLatestSkill = "RG_05";
            }
        }
        else if (value == 6)
        {
            output.getLeftChildSkillTree().getLeftChildSkillTree().getRightChildSkillTree().unlock();
            output.getLeftChildSkillTree().getLeftChildSkillTree().unlock();
            output.getLeftChildSkillTree().unlock();
            output.unlock();
            if (contents[0].equals("w"))
            {
                output.skillIdOfLatestSkill = "WR_06";
            }
            else if (contents[0].equals("m"))
            {
                output.skillIdOfLatestSkill = "MG_06";
            }
            else if (contents[0].equals("r"))
            {
                output.skillIdOfLatestSkill = "RG_06";
            }
        }
        else if (value == 7)
        {
            output.getLeftChildSkillTree().getLeftChildSkillTree().getRightChildSkillTree().getLeftChildSkillTree().unlock();
            output.getLeftChildSkillTree().getLeftChildSkillTree().getRightChildSkillTree().unlock();
            output.getLeftChildSkillTree().getLeftChildSkillTree().unlock();
            output.getLeftChildSkillTree().unlock();
            output.unlock();
            if (contents[0].equals("w"))
            {
                output.skillIdOfLatestSkill = "WR_07";
            }
            else if (contents[0].equals("m"))
            {
                output.skillIdOfLatestSkill = "MG_07";
            }
            else if (contents[0].equals("r"))
            {
                output.skillIdOfLatestSkill = "RG_07";
            }
        }
        else if (value == 8)
        {
            output.getLeftChildSkillTree().getLeftChildSkillTree().getRightChildSkillTree().getLeftChildSkillTree().getLeftChildSkillTree().unlock();
            output.getLeftChildSkillTree().getLeftChildSkillTree().getRightChildSkillTree().getLeftChildSkillTree().unlock();
            output.getLeftChildSkillTree().getLeftChildSkillTree().getRightChildSkillTree().unlock();
            output.getLeftChildSkillTree().getLeftChildSkillTree().unlock();
            output.getLeftChildSkillTree().unlock();
            output.unlock();

            if (contents[0].equals("w"))
            {
                output.skillIdOfLatestSkill = "WR_08";
            }
            else if (contents[0].equals("m"))
            {
                output.skillIdOfLatestSkill = "MG_08";
            }
            else if (contents[0].equals("r"))
            {
                output.skillIdOfLatestSkill = "RG_08";
            }
        }
        return output;
    }

}
