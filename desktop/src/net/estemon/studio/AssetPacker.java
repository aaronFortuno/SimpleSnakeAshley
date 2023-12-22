package net.estemon.studio;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class AssetPacker {

    public static final String RAW_ASSETS_PATH = "assets";
    public static final String ASSETS_PATH = "assets";

    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();

        TexturePacker.process(settings,
                RAW_ASSETS_PATH + "/gameplay",
                ASSETS_PATH + "/gameplay",
                "gameplay"
        );

        TexturePacker.process(settings,
                RAW_ASSETS_PATH + "/ui",
                ASSETS_PATH + "/ui",
                "ui"
                );
    }
}
