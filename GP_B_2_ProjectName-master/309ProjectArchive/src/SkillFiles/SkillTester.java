/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SkillFiles;

/**
 *
 * @author aaronhudson
 */
public class SkillTester 
{
    public static void main(String args[])
    {
        BinarySkillTree testerWarrior = Skill.generateWarriorSkillTree();
        BinarySkillTree testerMage = Skill.generateMageSkillTree();
        BinarySkillTree testerRogue = Skill.generateRogueSkillTree();
        System.out.println(testerWarrior.findSkill("WR_07").getData().toString());
        System.out.println(testerMage.findSkill("MG_05").getData().toString());
        System.out.println(testerRogue.findSkill("RG_08").getData().toString());
        //Skill testSkill = new Skill("WR_09", 9, false, "This is just to show that my constructor works. Hello World!");
        //System.out.println(testSkill.toString());
        //System.out.println(testSkill.getDescription());
    }
    
}
