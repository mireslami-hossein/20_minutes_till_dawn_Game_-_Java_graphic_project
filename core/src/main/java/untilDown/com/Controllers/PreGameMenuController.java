package untilDown.com.Controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import untilDown.com.Main;
import untilDown.com.Models.AbilityType;
import untilDown.com.Models.GameAssetManager;
import untilDown.com.Models.Setting;
import untilDown.com.Models.hero.HeroType;
import untilDown.com.Views.HintMenuView;
import untilDown.com.Views.PreGameMenuView;

public class PreGameMenuController {
    private PreGameMenuView preGameMenuView;
    private HintMenuView hintMenuView;

    public void setView(PreGameMenuView preGameMenuView) {
        this.preGameMenuView = preGameMenuView;
    }
    public void setView(HintMenuView view) {
        this.hintMenuView = view;
    }

    public TextButton getPlayButton() {
        TextButton playButton = new TextButton("Play", GameAssetManager.getManager().getSkin());
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Setting.durationOfGame = preGameMenuView.getSelectGameTime();
                Main.getMain().navigateToGame();
            }
        });
        return playButton;
    }

    public TextButton getBackButton() {
        TextButton backButton = new TextButton("Back", GameAssetManager.getManager().getSkin());
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.getMain().navigateToMainMenu();
            }
        });
        return backButton;
    }

    // Hint menu
    public SelectBox<HeroType> getHeroTypeSelectBox() {
        SelectBox<HeroType> selectHero = new SelectBox<>(GameAssetManager.getManager().getSkin());
        selectHero.setItems(HeroType.values());
        selectHero.setSelected(HeroType.Dasher);
        hintMenuView.setHeroHintLabel(HeroType.Dasher.getHintText());
        selectHero.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                HeroType selectedHeroType = selectHero.getSelected();
                hintMenuView.setHeroHintLabel(selectedHeroType.getHintText());
            }
        });
        return selectHero;
    }

    public SelectBox<AbilityType> getAbilityTypeSelectBox() {
        SelectBox<AbilityType> selectAbility = new SelectBox<>(GameAssetManager.getManager().getSkin());
        selectAbility.setItems(AbilityType.values());
        selectAbility.setSelected(AbilityType.Speedy);
        hintMenuView.setAbilityHintLabel(AbilityType.Speedy.getHintText());
        selectAbility.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                AbilityType selectedAbilityType = selectAbility.getSelected();
                hintMenuView.setAbilityHintLabel(selectedAbilityType.getHintText());
            }
        });
        return selectAbility;
    }


}
