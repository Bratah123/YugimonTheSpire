import basemod.BaseMod;
import basemod.abstracts.CustomCard;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.PostCreateStartingDeckSubscriber;
import basemod.interfaces.PostDrawSubscriber;
import color.AbstractCardEnum;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import customcards.GojoDefend;
import customcards.GojoStrike;
import customcards.InfiniteVoid;
import customclasses.Gojo;
import customclasses.enums.GojoClassEnum;

import java.util.ArrayList;
import java.util.List;

@SpireInitializer
public class ModInitializer implements PostDrawSubscriber,
        EditCardsSubscriber, PostCreateStartingDeckSubscriber, EditCharactersSubscriber {


    public ModInitializer() {
        // Use this for when you subscribe to any hooks offered by BaseMod.
        BaseMod.subscribe(this);
        BaseMod.addColor(AbstractCardEnum.GOJO_COLOR,
                Color.PURPLE,
                "img/gojo/background/512/bg_attack_purple.png",
                "img/gojo/background/512/bg_skill_purple.png",
                "img/gojo/background/512/bg_power_purple.png",
                "img/gojo/background/512/card_purple_orb.png",
                "img/gojo/background/1024/bg_attack_purple.png",
                "img/gojo/background/1024/bg_skill_purple.png",
                "img/gojo/background/1024/bg_power_purple.png",
                "img/gojo/background/1024/card_purple_orb.png",
                "img/gojo/background/512/card_purple_small_orb.png"
                );
    }

    //Used by @SpireInitializer
    public static void initialize() {

        //This creates an instance of our classes and gets our code going after BaseMod and ModTheSpire initialize.
        ModInitializer modInitializer = new ModInitializer();

        //Look at the BaseMod wiki for getting started. (Skip the decompiling part. It's not needed right now)

    }

    @Override
    public void receivePostDraw(AbstractCard abstractCard) {
        System.out.println("Player just drew card: " + abstractCard.name);
    }

    @Override
    public void receiveEditCards() {
        System.out.println("Adding custom cards");
        List<CustomCard> cards = new ArrayList<>();
        cards.add(new InfiniteVoid());
        cards.add(new GojoStrike());
        cards.add(new GojoDefend());

        for (CustomCard card : cards) {
            BaseMod.addCard(card);
            UnlockTracker.unlockCard(card.cardID);
        }
        System.out.println("Finished adding custom cards");

    }

    @Override
    public void receivePostCreateStartingDeck(AbstractPlayer.PlayerClass playerClass, CardGroup cardGroup) {
        System.out.println("#receivePostCreateStartingDeck.");
    }

    @Override
    public void receiveEditCharacters() {
        // AbstractPlayer character, String selectButtonPath, String portraitPath, PlayerClass characterID
        System.out.println("Adding custom characters");
        BaseMod.addCharacter(new Gojo("Gojo Satoru", GojoClassEnum.GOJO),
                "img/gojo/buttons/seeker_button.png",
                "img/gojo/gojo-portrait-1920x1200.jpg",
                GojoClassEnum.GOJO
                );
    }
}