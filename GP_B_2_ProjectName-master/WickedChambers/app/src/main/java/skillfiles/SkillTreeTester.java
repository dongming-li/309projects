package skillfiles;

/**
 * Created by Antonio on 10/24/2017.
 */

public class SkillTreeTester
{
    public static void main(String[] args)
    {
        SkillTree tester = SkillTree.generateWarriorSkillTree();
        String ser = tester.serialize();
        System.out.println(ser);
        tester.unlockNode("WR_01");
        System.out.println(tester.serialize());
        tester.unlockNode("WR_01");
        System.out.println(tester.serialize());
        tester.unlockNode("WR_02");
        System.out.println(tester.serialize());
        String toPush = tester.serialize();
        SkillTree newTester = SkillTree.deserialize(toPush);
        System.out.println(newTester.serialize());
    }

}
