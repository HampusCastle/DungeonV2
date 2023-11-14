package com.hampusborg.demo.monsters;

public class WitchKing extends AMonster {


    public WitchKing() {
        super("Witch King", 10, 10, 10, 10);
    }

    @Override
    public String attack() {
        return "Flying around throwing orcs at you";
    }

    @Override
    public String getLore() {
        return "The Witch-king of Angmar, AKA the Nazgûl Kingpin, was Sauron's right-hand wraith, the one who thought, \"Why settle for a cool title when you can have nine?\" " +
                "Famous for being the head honcho of the Ringwraiths, he was like the evil overlord of a ghostly boy band. His favorite pastime? " +
                "Terrorizing the realms of men and spreading more fear than a thousand haunted houses. " +
                "If he had a LinkedIn profile, his skills section would include screaming, riding winged creatures, and generally being an incorporeal party pooper." +
                " Plus, he had a nifty magic sword that could ruin anyone's day. But let's not forget his claim to fame – getting a fancy prophecy about not being killed by a man, only to be foiled by Eowyn, a woman." +
                " It's like he never read the fine print in his evil contract. Better luck next time, Witch-king.";
    }
}
