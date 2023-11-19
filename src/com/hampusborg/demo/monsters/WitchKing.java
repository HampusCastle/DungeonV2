package com.hampusborg.demo.monsters;

import com.hampusborg.demo.interfaces.IColors;

public class WitchKing extends AMonster implements IColors {


    public WitchKing() {
        super("Witch King", 35, 60, 43, 35);
    }

    @Override
    public String attack() {
        return "Flying around throwing orcs at you";
    }

    @Override
    public String getLore() {
        return BLUE + "The Witch-king of Angmar, AKA the Nazgûl Kingpin, was Sauron's right-hand wraith, the one who thought,\n \"Why settle for a cool title when you can have nine?\" " +
                "Famous for being the head honcho of the Ringwraiths, he was like the evil overlord of a ghostly boy band. His favorite pastime?\n " +
                "Terrorizing the realms of men and spreading more fear than a thousand haunted houses. \n" +
                "If he had a LinkedIn profile, his skills section would include screaming, riding winged creatures, and generally being an incorporeal party pooper.\n" +
                " Plus, he had a nifty magic sword that could ruin anyone's day.\n But let's not forget his claim to fame – getting a fancy prophecy about not being killed by a man, only to be foiled by Eowyn, a woman.\n" +
                " It's like he never read the fine print in his evil contract. Better luck next time, Witch-king." + RESET;
    }
}
