package com.torrescalazans.baking.ui.main;

import java.util.List;

import com.torrescalazans.baking.data.model.Ribot;
import com.torrescalazans.baking.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

}
