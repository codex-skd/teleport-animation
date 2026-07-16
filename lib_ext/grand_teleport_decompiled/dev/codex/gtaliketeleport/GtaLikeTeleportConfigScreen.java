/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Font
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.gui.components.AbstractWidget
 *  net.minecraft.client.gui.components.Button
 *  net.minecraft.client.gui.components.EditBox
 *  net.minecraft.client.gui.components.Tooltip
 *  net.minecraft.client.gui.components.events.GuiEventListener
 *  net.minecraft.client.gui.narration.NarratedElementType
 *  net.minecraft.client.gui.narration.NarrationElementOutput
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.FormattedText
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 */
package dev.codex.gtaliketeleport;

import dev.codex.gtaliketeleport.GtaLikeTeleportClientNetworking;
import dev.codex.gtaliketeleport.GtaLikeTeleportConfig;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

final class GtaLikeTeleportConfigScreen
extends Screen {
    private static final int PANEL_COLOR = 0x66000000;
    private static final int PANEL_BORDER_COLOR = 0x77FFFFFF;
    private static final int DEBUG_BORDER_COLOR = -171;
    private static final int DEBUG_HANDLE_COLOR = -855638187;
    private static final int DEBUG_ITEM_BORDER_COLOR = -1996488875;
    private static final int TEXT_COLOR = -1;
    private static final int MUTED_TEXT_COLOR = -6250336;
    private static final int OUTER_PADDING_LEFT = 14;
    private static final int OUTER_PADDING_TOP = 14;
    private static final int OUTER_PADDING_RIGHT = 14;
    private static final int OUTER_PADDING_BOTTOM = 0;
    private static final int MIN_PANEL_WIDTH = 300;
    private static final int MIN_PANEL_HEIGHT = 142;
    private static final int MAX_PANEL_MARGIN = 28;
    private static final int DEBUG_TOOLBAR_MARGIN = 6;
    private static final int DEBUG_TOOLBAR_BUTTON_HEIGHT = 20;
    private static final int DEBUG_TOOLBAR_GAP = 6;
    private static final int DEBUG_TOOLBAR_ROW_STEP = 22;
    private static final int DEBUG_PANEL_TOP_MARGIN = 84;
    private static final double MAX_CONTENT_WIDTH_RATIO = 0.72;
    private static final int RESIZE_HIT_SIZE = 10;
    private static final int SNAP_DISTANCE = 5;
    private static final int GRID_SIZE = 8;
    private static final int DEBUG_GRID_COLOR = 0x22FFFFFF;
    private static final int DEBUG_CENTER_GUIDE_COLOR = 2007682303;
    private static final int DEBUG_PANEL_GUIDE_COLOR = 0x66FFFFFF;
    private static final int DEBUG_SELECTED_GUIDE_COLOR = -1996488875;
    private static final double DEFAULT_PANEL_WIDTH_RATIO = 0.58;
    private static final double DEFAULT_LINKED_PANEL_HEIGHT_RATIO = 0.24;
    private static final double DEFAULT_UNLINKED_PANEL_HEIGHT_RATIO = 0.31;
    private static final ResourceLocation OVERWORLD_DIMENSION_ICON = new ResourceLocation("minecraft", "textures/block/grass_block_side.png");
    private static final ResourceLocation NETHER_DIMENSION_ICON = new ResourceLocation("minecraft", "textures/block/netherrack.png");
    private static final ResourceLocation END_DIMENSION_ICON = new ResourceLocation("minecraft", "textures/block/end_stone.png");
    private static final String ITEM_PANEL = "panel";
    private static final String ITEM_TITLE = "title";
    private static final String ITEM_DESCRIPTION = "description";
    private static final String ITEM_STATUS_LINKED = "status_linked";
    private static final String ITEM_STATUS_UNLINKED = "status_unlinked";
    private static final String ITEM_LINKED_SLIDER = "linked_slider";
    private static final String ITEM_ZOOM_OUT_SLIDER = "zoom_out_slider";
    private static final String ITEM_ZOOM_IN_SLIDER = "zoom_in_slider";
    private static final String ITEM_LINK_BUTTON = "link_button";
    private static final String ITEM_DIMENSION_OVERWORLD = "dimension_overworld";
    private static final String ITEM_DIMENSION_NETHER = "dimension_nether";
    private static final String ITEM_DIMENSION_END = "dimension_end";
    private static final String ITEM_RESET_BUTTON = "reset_button";
    private static final String ITEM_DONE_BUTTON = "done_button";
    private static final String ITEM_PREV_PAGE_BUTTON = "prev_page_button";
    private static final String ITEM_NEXT_PAGE_BUTTON = "next_page_button";
    private static final String ITEM_TAB_GENERAL = "tab_general";
    private static final String ITEM_TAB_ZOOM_STAGE = "tab_zoom_stage";
    private static final String ITEM_TAB_ZOOM_STAGE_2 = "tab_zoom_stage_2";
    private static final String ITEM_TAB_SOUNDS = "tab_sounds";
    private static final String ITEM_TAB_OTHERS = "tab_others";
    private static final String ITEM_GENERAL_TITLE = "general_title";
    private static final String ITEM_GENERAL_DESCRIPTION = "general_description";
    private static final String ITEM_EFFECT_LABEL = "effect_label";
    private static final String ITEM_EFFECT_TOGGLE = "effect_toggle";
    private static final String ITEM_MOVEMENT_LABEL = "movement_label";
    private static final String ITEM_MOVEMENT_TOGGLE = "movement_toggle";
    private static final String ITEM_CROSS_DIMENSION_TRAVEL_LABEL = "cross_dimension_travel_label";
    private static final String ITEM_CROSS_DIMENSION_TRAVEL_TOGGLE = "cross_dimension_travel_toggle";
    private static final String ITEM_ADVANCED1_TITLE = "advanced1_title";
    private static final String ITEM_ADVANCED1_DESCRIPTION = "advanced1_description";
    private static final String ITEM_ADVANCED2_TITLE = "advanced2_title";
    private static final String ITEM_ADVANCED2_DESCRIPTION = "advanced2_description";
    private static final String ITEM_ADVANCED3_TITLE = "advanced3_title";
    private static final String ITEM_ADVANCED3_DESCRIPTION = "advanced3_description";
    private static final String ITEM_ZOOM_STAGE_GLIDE_SLIDER = "zoom_stage_glide_slider";
    private static final String ITEM_ZOOM_STAGE_GLIDE_TICKS_LABEL = "zoom_stage_glide_ticks_label";
    private static final String ITEM_ZOOM_STAGE_GLIDE_TICKS_FIELD = "zoom_stage_glide_ticks_field";
    private static final String ITEM_ZOOM_OUT_TICKS_LABEL = "zoom_out_ticks_label";
    private static final String ITEM_ZOOM_OUT_TICKS_FIELD = "zoom_out_ticks_field";
    private static final String ITEM_ZOOM_IN_TICKS_LABEL = "zoom_in_ticks_label";
    private static final String ITEM_ZOOM_IN_TICKS_FIELD = "zoom_in_ticks_field";
    private static final String ITEM_BODY_HEIGHT_SLIDER = "body_height_slider";
    private static final String ITEM_BODY_GLIDE_SLIDER = "body_glide_slider";
    private static final String ITEM_BODY_GLIDE_TICKS_LABEL = "body_glide_ticks_label";
    private static final String ITEM_BODY_GLIDE_TICKS_FIELD = "body_glide_ticks_field";
    private static final String ITEM_PLAYER_HIDE_LABEL = "player_hide_label";
    private static final String ITEM_PLAYER_HIDE_TICKS_FIELD = "player_hide_ticks_field";
    private static final String ITEM_SOUNDS_TITLE = "sounds_title";
    private static final String ITEM_SOUNDS_DESCRIPTION = "sounds_description";
    private static final String ITEM_SOUND_MODE_LABEL = "sound_mode_label";
    private static final String ITEM_SOUND_MODE_TOGGLE = "sound_mode_toggle";
    private static final String ITEM_MINECRAFT_VOLUME_SLIDER = "minecraft_volume_slider";
    private static final String ITEM_CUSTOM_VOLUME_SLIDER = "custom_volume_slider";
    private static final String ITEM_OTHERS_TITLE = "others_title";
    private static final String ITEM_OTHERS_DESCRIPTION = "others_description";
    private static final String ITEM_WARP_PLATE_LABEL = "warp_plate_label";
    private static final String ITEM_WARP_PLATE_TOGGLE = "warp_plate_toggle";
    private static final String ITEM_EXTERNAL_TELEPORT_LABEL = "external_teleport_label";
    private static final String ITEM_EXTERNAL_TELEPORT_TOGGLE = "external_teleport_toggle";
    private static final String ITEM_FALLBACK_CHUNK_FADE_LABEL = "fallback_chunk_fade_label";
    private static final String ITEM_FALLBACK_CHUNK_FADE_TOGGLE = "fallback_chunk_fade_toggle";
    private final Screen parent;
    private ConfigPage currentPage = ConfigPage.GENERAL;
    private boolean linked;
    private boolean effectEnabled;
    private boolean movementAllowed;
    private boolean crossDimensionTravelEnabled;
    private boolean layoutDebugEnabled;
    private boolean layoutAspectLocked;
    private boolean layoutGridEnabled;
    private boolean layoutSnapEnabled;
    private boolean updatingValueWidgets;
    private double[] zoomOutHeights;
    private double[] zoomInHeights;
    private GtaLikeTeleportConfig.ZoomDimension selectedZoomDimension;
    private int[] zoomOutStageTicks;
    private int[] zoomInStageTicks;
    private double zoomStageGlideHeight;
    private int zoomStageGlideTicks;
    private double bodyCameraHeight;
    private double bodyGlideHeight;
    private int bodyGlideTicks;
    private int localPlayerHideTicks;
    private boolean customSoundsEnabled;
    private double minecraftSoundVolume;
    private double customSoundVolume;
    private boolean warpPlateTransitionsEnabled;
    private boolean externalTeleportTransitionsEnabled;
    private boolean fallbackChunkFadeEnabled;
    private StageHeightSlider linkedSlider;
    private StageHeightSlider zoomOutSlider;
    private StageHeightSlider zoomInSlider;
    private SingleValueSlider zoomStageGlideSlider;
    private SingleValueSlider bodyHeightSlider;
    private SingleValueSlider bodyGlideSlider;
    private SingleValueSlider minecraftSoundVolumeSlider;
    private SingleValueSlider customSoundVolumeSlider;
    private EditBox zoomStageGlideTicksEditBox;
    private EditBox zoomOutTicksEditBox;
    private EditBox zoomInTicksEditBox;
    private EditBox bodyGlideTicksEditBox;
    private EditBox playerHideTicksEditBox;
    private LinkLockButton linkButton;
    private DimensionIconButton overworldDimensionButton;
    private DimensionIconButton netherDimensionButton;
    private DimensionIconButton endDimensionButton;
    private Button resetButton;
    private Button doneButton;
    private Button prevPageButton;
    private Button nextPageButton;
    private Button[] pageTabButtons = new Button[0];
    private Button effectToggleButton;
    private Button movementToggleButton;
    private Button crossDimensionTravelToggleButton;
    private Button soundModeToggleButton;
    private Button warpPlateToggleButton;
    private Button externalTeleportToggleButton;
    private Button fallbackChunkFadeToggleButton;
    private Button layoutDebugButton;
    private Button aspectButton;
    private Button resetLayoutButton;
    private Button resetItemSizeButton;
    private Button gridButton;
    private Button snapButton;
    private EditBox textEditBox;
    private EditBox layoutValueEditBox;
    private Button applyLayoutValueButton;
    private boolean textEditorVisible;
    private boolean layoutValueEditorVisible;
    private boolean updatingTextEditor;
    private boolean updatingLayoutValueEditor;
    private String selectedLayoutItem = "panel";
    private String editingLayoutItem;
    private LayoutEditAction layoutEditAction = LayoutEditAction.NONE;
    private LayoutRect editStartRect;
    private LayoutRect editingRect;
    private double editStartMouseX;
    private double editStartMouseY;
    private int sessionLayoutBaseWidth;
    private int sessionLayoutBaseHeight;

    GtaLikeTeleportConfigScreen(Screen parent) {
        super((Component)Component.m_237113_((String)"Grand Teleport Settings"));
        this.parent = parent;
        this.selectedZoomDimension = GtaLikeTeleportConfigScreen.getInitialZoomDimension();
        this.linked = GtaLikeTeleportConfig.areZoomHeightsLinked(this.selectedZoomDimension);
        this.effectEnabled = GtaLikeTeleportConfig.isEffectEnabled();
        this.movementAllowed = !GtaLikeTeleportConfig.isPlayerFreezeEnabled();
        this.crossDimensionTravelEnabled = GtaLikeTeleportConfig.isCrossDimensionTravelEnabled();
        this.layoutDebugEnabled = GtaLikeTeleportConfig.isConfigLayoutEditorButtonVisible() && GtaLikeTeleportConfig.isConfigLayoutDebugEnabled();
        this.layoutAspectLocked = GtaLikeTeleportConfig.isConfigLayoutAspectLocked();
        this.layoutGridEnabled = GtaLikeTeleportConfig.isConfigLayoutGridEnabled();
        this.layoutSnapEnabled = GtaLikeTeleportConfig.isConfigLayoutSnapEnabled();
        this.zoomOutHeights = GtaLikeTeleportConfig.getZoomOutStageHeights(this.selectedZoomDimension);
        this.zoomInHeights = GtaLikeTeleportConfig.getRawZoomInStageHeights(this.selectedZoomDimension);
        this.zoomOutStageTicks = GtaLikeTeleportConfig.getZoomOutStageTicks();
        this.zoomInStageTicks = GtaLikeTeleportConfig.getZoomInStageTicks();
        this.zoomStageGlideHeight = GtaLikeTeleportConfig.getZoomStageGlideHeight();
        this.zoomStageGlideTicks = GtaLikeTeleportConfig.getZoomStageGlideTicks();
        this.bodyCameraHeight = GtaLikeTeleportConfig.getBodyCameraHeight();
        this.bodyGlideHeight = GtaLikeTeleportConfig.getBodyGlideHeight();
        this.bodyGlideTicks = GtaLikeTeleportConfig.getBodyGlideTicks();
        this.localPlayerHideTicks = GtaLikeTeleportConfig.getLocalPlayerHideTicks();
        this.customSoundsEnabled = GtaLikeTeleportConfig.isCustomSoundsEnabled();
        this.minecraftSoundVolume = GtaLikeTeleportConfig.getMinecraftSoundVolume();
        this.customSoundVolume = GtaLikeTeleportConfig.getCustomSoundVolume();
        this.warpPlateTransitionsEnabled = GtaLikeTeleportConfig.isWarpPlateTransitionsEnabled();
        this.externalTeleportTransitionsEnabled = GtaLikeTeleportConfig.isExternalTeleportTransitionsEnabled();
        this.fallbackChunkFadeEnabled = GtaLikeTeleportConfig.isFallbackChunkFadeEnabled();
    }

    private static GtaLikeTeleportConfig.ZoomDimension getInitialZoomDimension() {
        Minecraft client = Minecraft.m_91087_();
        return GtaLikeTeleportConfig.ZoomDimension.fromLevel(client.f_91073_ == null ? null : client.f_91073_.m_46472_());
    }

    private void loadZoomHeightState() {
        this.linked = GtaLikeTeleportConfig.areZoomHeightsLinked(this.selectedZoomDimension);
        this.zoomOutHeights = GtaLikeTeleportConfig.getZoomOutStageHeights(this.selectedZoomDimension);
        this.zoomInHeights = GtaLikeTeleportConfig.getRawZoomInStageHeights(this.selectedZoomDimension);
        if (this.linked) {
            this.zoomInHeights = (double[])this.zoomOutHeights.clone();
        }
    }

    protected void m_7856_() {
        if (this.sessionLayoutBaseWidth <= 0 || this.sessionLayoutBaseHeight <= 0) {
            this.sessionLayoutBaseWidth = this.f_96543_;
            this.sessionLayoutBaseHeight = this.f_96544_;
        }
        this.resetWidgetReferences();
        LayoutRect panel = this.getContentRect();
        int y = this.getFirstSliderY(panel);
        if (this.currentPage == ConfigPage.ZOOM) {
            this.initZoomWidgets();
        } else if (this.currentPage == ConfigPage.GENERAL) {
            this.initGeneralWidgets();
        } else if (this.currentPage == ConfigPage.ZOOM_STAGE_2) {
            this.initAdvancedTwoWidgets();
        } else if (this.currentPage == ConfigPage.SOUNDS) {
            this.initSoundWidgets();
        } else if (this.currentPage == ConfigPage.OTHERS) {
            this.initOthersWidgets();
        }
        this.initPageNavigationWidgets();
        this.initPageTabWidgets();
        if (GtaLikeTeleportConfig.isConfigLayoutEditorButtonVisible()) {
            this.layoutDebugButton = (Button)this.m_142416_((GuiEventListener)Button.m_253074_((Component)this.getLayoutDebugLabel(), button -> this.toggleLayoutDebug()).m_252987_(panel.x + panel.width - 94, panel.y + 4, 90, 20).m_257505_(Tooltip.m_257550_((Component)Component.m_237115_((String)"gtalike_teleport.config.layout_debug.tooltip"))).m_253136_());
        }
        if (this.layoutDebugEnabled) {
            this.aspectButton = (Button)this.m_142416_((GuiEventListener)Button.m_253074_((Component)this.getAspectLabel(), button -> this.toggleAspectLock()).m_252987_(panel.x + 4, panel.y + 4, 90, 20).m_257505_(Tooltip.m_257550_((Component)Component.m_237115_((String)"gtalike_teleport.config.layout_aspect.tooltip"))).m_253136_());
            this.resetLayoutButton = (Button)this.m_142416_((GuiEventListener)Button.m_253074_((Component)Component.m_237115_((String)"gtalike_teleport.config.layout_reset"), button -> this.resetLayout()).m_252987_(panel.x + 98, panel.y + 4, 98, 20).m_253136_());
            this.resetItemSizeButton = (Button)this.m_142416_((GuiEventListener)Button.m_253074_((Component)Component.m_237115_((String)"gtalike_teleport.config.layout_size_reset"), button -> this.resetSelectedItemSize()).m_252987_(panel.x + 200, panel.y + 4, 88, 20).m_257505_(Tooltip.m_257550_((Component)Component.m_237115_((String)"gtalike_teleport.config.layout_size_reset.tooltip"))).m_253136_());
            this.gridButton = (Button)this.m_142416_((GuiEventListener)Button.m_253074_((Component)this.getGridLabel(), button -> this.toggleGrid()).m_252987_(panel.x + 292, panel.y + 4, 78, 20).m_257505_(Tooltip.m_257550_((Component)Component.m_237115_((String)"gtalike_teleport.config.layout_grid.tooltip"))).m_253136_());
            this.snapButton = (Button)this.m_142416_((GuiEventListener)Button.m_253074_((Component)this.getSnapLabel(), button -> this.toggleSnap()).m_252987_(panel.x + 282, panel.y + 4, 78, 20).m_257505_(Tooltip.m_257550_((Component)Component.m_237115_((String)"gtalike_teleport.config.layout_snap.tooltip"))).m_253136_());
            this.textEditBox = (EditBox)this.m_142416_((GuiEventListener)new EditBox(this.f_96547_, panel.x, y, 240, 20, (Component)Component.m_237115_((String)"gtalike_teleport.config.text_editor")));
            this.textEditBox.m_94199_(128);
            this.textEditBox.m_94151_(value -> {
                if (!this.updatingTextEditor && this.itemSupportsText(this.selectedLayoutItem)) {
                    GtaLikeTeleportConfig.setConfigText(this.selectedLayoutItem, value);
                    this.applyItemText(this.selectedLayoutItem);
                }
            });
            this.layoutValueEditBox = (EditBox)this.m_142416_((GuiEventListener)new EditBox(this.f_96547_, panel.x, y + 24, 270, 20, (Component)Component.m_237115_((String)"gtalike_teleport.config.layout_values")));
            this.layoutValueEditBox.m_94199_(80);
            this.applyLayoutValueButton = (Button)this.m_142416_((GuiEventListener)Button.m_253074_((Component)Component.m_237115_((String)"gtalike_teleport.config.layout_values_apply"), button -> this.applyLayoutValuesFromEditor()).m_252987_(panel.x + 274, y + 24, 64, 20).m_257505_(Tooltip.m_257550_((Component)Component.m_237115_((String)"gtalike_teleport.config.layout_values.tooltip"))).m_253136_());
        }
        LayoutRect resetRect = this.getItemRect(ITEM_RESET_BUTTON);
        this.resetButton = (Button)this.m_142416_((GuiEventListener)Button.m_253074_((Component)Component.m_237119_(), button -> this.resetCurrentPage()).m_252987_(resetRect.x, resetRect.y, resetRect.width, resetRect.height).m_253136_());
        LayoutRect doneRect = this.getItemRect(ITEM_DONE_BUTTON);
        this.doneButton = (Button)this.m_142416_((GuiEventListener)Button.m_253074_((Component)Component.m_237119_(), button -> this.m_7379_()).m_252987_(doneRect.x, doneRect.y, doneRect.width, doneRect.height).m_253136_());
        this.repositionWidgets();
        this.refreshTextEditor();
        this.refreshLayoutValueEditor();
    }

    private void resetWidgetReferences() {
        this.zoomOutSlider = null;
        this.zoomInSlider = null;
        this.zoomStageGlideSlider = null;
        this.bodyHeightSlider = null;
        this.bodyGlideSlider = null;
        this.minecraftSoundVolumeSlider = null;
        this.customSoundVolumeSlider = null;
        this.zoomStageGlideTicksEditBox = null;
        this.zoomOutTicksEditBox = null;
        this.zoomInTicksEditBox = null;
        this.bodyGlideTicksEditBox = null;
        this.playerHideTicksEditBox = null;
        this.linkButton = null;
        this.prevPageButton = null;
        this.nextPageButton = null;
        this.pageTabButtons = new Button[0];
        this.effectToggleButton = null;
        this.movementToggleButton = null;
        this.crossDimensionTravelToggleButton = null;
        this.soundModeToggleButton = null;
        this.warpPlateToggleButton = null;
        this.externalTeleportToggleButton = null;
        this.fallbackChunkFadeToggleButton = null;
        this.resetButton = null;
        this.doneButton = null;
        this.layoutDebugButton = null;
        this.aspectButton = null;
        this.resetLayoutButton = null;
        this.resetItemSizeButton = null;
        this.gridButton = null;
        this.snapButton = null;
        this.textEditBox = null;
        this.layoutValueEditBox = null;
        this.applyLayoutValueButton = null;
    }

    private void initZoomWidgets() {
        LayoutRect outSliderRect = this.getItemRect(ITEM_ZOOM_OUT_SLIDER);
        this.zoomOutSlider = (StageHeightSlider)this.m_142416_((GuiEventListener)new StageHeightSlider(outSliderRect.x, outSliderRect.y, outSliderRect.width, this.getItemComponent(ITEM_ZOOM_OUT_SLIDER), this.zoomOutHeights, values -> {
            this.zoomOutHeights = values;
            if (this.linked) {
                this.zoomInHeights = (double[])values.clone();
                if (this.zoomInSlider != null) {
                    this.zoomInSlider.setValues(this.zoomInHeights);
                }
            }
            this.saveHeights();
        }));
        if (this.linked) {
            this.zoomInHeights = (double[])this.zoomOutHeights.clone();
        }
        LayoutRect inSliderRect = this.getItemRect(ITEM_ZOOM_IN_SLIDER);
        this.zoomInSlider = (StageHeightSlider)this.m_142416_((GuiEventListener)new StageHeightSlider(inSliderRect.x, inSliderRect.y, inSliderRect.width, this.getItemComponent(ITEM_ZOOM_IN_SLIDER), this.zoomInHeights, values -> {
            if (!this.linked) {
                this.zoomInHeights = values;
                this.saveHeights();
            }
        }));
        this.zoomInSlider.setEditable(!this.linked);
        LayoutRect linkRect = this.getItemRect(ITEM_LINK_BUTTON);
        this.linkButton = (LinkLockButton)this.m_142416_((GuiEventListener)new LinkLockButton(linkRect.x, linkRect.y, linkRect.width, linkRect.height, this::toggleLinked));
        this.overworldDimensionButton = this.addDimensionButton(ITEM_DIMENSION_OVERWORLD, GtaLikeTeleportConfig.ZoomDimension.OVERWORLD, GtaLikeTeleportConfigScreen.safeDefaultItemStack(() -> ((Item)Items.f_42276_).m_7968_()), (Component)Component.m_237113_((String)"Overworld"), OVERWORLD_DIMENSION_ICON);
        this.netherDimensionButton = this.addDimensionButton(ITEM_DIMENSION_NETHER, GtaLikeTeleportConfig.ZoomDimension.NETHER, GtaLikeTeleportConfigScreen.safeDefaultItemStack(() -> ((Item)Items.f_42048_).m_7968_()), (Component)Component.m_237113_((String)"The Nether"), NETHER_DIMENSION_ICON);
        this.endDimensionButton = this.addDimensionButton(ITEM_DIMENSION_END, GtaLikeTeleportConfig.ZoomDimension.END, GtaLikeTeleportConfigScreen.safeDefaultItemStack(() -> ((Item)Items.f_42102_).m_7968_()), (Component)Component.m_237113_((String)"The End"), END_DIMENSION_ICON);
        this.updateLinkButton();
        this.updateDimensionButtons();
    }

    private static ItemStack safeDefaultItemStack(Supplier<ItemStack> stackFactory) {
        try {
            ItemStack stack = stackFactory.get();
            return stack == null ? ItemStack.f_41583_ : stack;
        }
        catch (LinkageError | RuntimeException error) {
            return ItemStack.f_41583_;
        }
    }

    private DimensionIconButton addDimensionButton(String item, GtaLikeTeleportConfig.ZoomDimension dimension, ItemStack stack, Component label, ResourceLocation texture) {
        LayoutRect rect = this.getItemRect(item);
        return (DimensionIconButton)this.m_142416_((GuiEventListener)new DimensionIconButton(rect.x, rect.y, rect.width, rect.height, stack, label, texture, () -> this.switchZoomDimension(dimension)));
    }

    private void initGeneralWidgets() {
        LayoutRect effectRect = this.getItemRect(ITEM_EFFECT_TOGGLE);
        this.effectToggleButton = (Button)this.m_142416_((GuiEventListener)Button.m_253074_((Component)Component.m_237119_(), button -> this.toggleEffectEnabled()).m_252987_(effectRect.x, effectRect.y, effectRect.width, effectRect.height).m_253136_());
        LayoutRect movementRect = this.getItemRect(ITEM_MOVEMENT_TOGGLE);
        this.movementToggleButton = (Button)this.m_142416_((GuiEventListener)Button.m_253074_((Component)Component.m_237119_(), button -> this.toggleMovementAllowed()).m_252987_(movementRect.x, movementRect.y, movementRect.width, movementRect.height).m_253136_());
        LayoutRect crossDimensionRect = this.getItemRect(ITEM_CROSS_DIMENSION_TRAVEL_TOGGLE);
        this.crossDimensionTravelToggleButton = (Button)this.m_142416_((GuiEventListener)Button.m_253074_((Component)Component.m_237119_(), button -> this.toggleCrossDimensionTravel()).m_252987_(crossDimensionRect.x, crossDimensionRect.y, crossDimensionRect.width, crossDimensionRect.height).m_253136_());
    }

    private void initOthersWidgets() {
        LayoutRect warpPlateRect = this.getItemRect(ITEM_WARP_PLATE_TOGGLE);
        this.warpPlateToggleButton = (Button)this.m_142416_((GuiEventListener)Button.m_253074_((Component)Component.m_237119_(), button -> this.toggleWarpPlateTransitions()).m_252987_(warpPlateRect.x, warpPlateRect.y, warpPlateRect.width, warpPlateRect.height).m_253136_());
        LayoutRect externalRect = this.getItemRect(ITEM_EXTERNAL_TELEPORT_TOGGLE);
        this.externalTeleportToggleButton = (Button)this.m_142416_((GuiEventListener)Button.m_253074_((Component)Component.m_237119_(), button -> this.toggleExternalTeleportTransitions()).m_252987_(externalRect.x, externalRect.y, externalRect.width, externalRect.height).m_253136_());
        this.updateOthersButtons();
    }

    private void initSoundWidgets() {
        LayoutRect modeRect = this.getItemRect(ITEM_SOUND_MODE_TOGGLE);
        this.soundModeToggleButton = (Button)this.m_142416_((GuiEventListener)Button.m_253074_((Component)Component.m_237119_(), button -> this.toggleCustomSoundsEnabled()).m_252987_(modeRect.x, modeRect.y, modeRect.width, modeRect.height).m_253136_());
        LayoutRect minecraftVolumeRect = this.getItemRect(ITEM_MINECRAFT_VOLUME_SLIDER);
        this.minecraftSoundVolumeSlider = (SingleValueSlider)this.m_142416_((GuiEventListener)new SingleValueSlider(minecraftVolumeRect.x, minecraftVolumeRect.y, minecraftVolumeRect.width, this.getItemComponent(ITEM_MINECRAFT_VOLUME_SLIDER), this.minecraftSoundVolume, GtaLikeTeleportConfig.getMinSoundVolume(), GtaLikeTeleportConfig.getMaxSoundVolume(), 0.1, false, "x", value -> {
            this.minecraftSoundVolume = value;
            GtaLikeTeleportConfig.setMinecraftSoundVolume(value);
        }));
        LayoutRect customVolumeRect = this.getItemRect(ITEM_CUSTOM_VOLUME_SLIDER);
        this.customSoundVolumeSlider = (SingleValueSlider)this.m_142416_((GuiEventListener)new SingleValueSlider(customVolumeRect.x, customVolumeRect.y, customVolumeRect.width, this.getItemComponent(ITEM_CUSTOM_VOLUME_SLIDER), this.customSoundVolume, GtaLikeTeleportConfig.getMinSoundVolume(), GtaLikeTeleportConfig.getMaxSoundVolume(), 0.1, false, "x", value -> {
            this.customSoundVolume = value;
            GtaLikeTeleportConfig.setCustomSoundVolume(value);
        }));
        this.updateSoundButtons();
    }

    private void initAdvancedOneWidgets() {
        LayoutRect glideRect = this.getItemRect(ITEM_ZOOM_STAGE_GLIDE_SLIDER);
        this.zoomStageGlideSlider = (SingleValueSlider)this.m_142416_((GuiEventListener)new SingleValueSlider(glideRect.x, glideRect.y, glideRect.width, this.getItemComponent(ITEM_ZOOM_STAGE_GLIDE_SLIDER), this.zoomStageGlideHeight, GtaLikeTeleportConfig.getMinZoomStageGlideHeight(), GtaLikeTeleportConfig.getMaxZoomStageGlideHeight(), 0.1, false, " blocks", value -> {
            this.zoomStageGlideHeight = value;
            GtaLikeTeleportConfig.setZoomStageGlideHeight(value);
        }));
        LayoutRect ticksRect = this.getItemRect(ITEM_ZOOM_STAGE_GLIDE_TICKS_FIELD);
        this.zoomStageGlideTicksEditBox = this.addTickEditBox(ticksRect, this.zoomStageGlideTicks, value -> {
            this.zoomStageGlideTicks = value;
            GtaLikeTeleportConfig.setZoomStageGlideTicks(value);
        });
    }

    private void initAdvancedTwoWidgets() {
        LayoutRect outRect = this.getItemRect(ITEM_ZOOM_OUT_TICKS_FIELD);
        this.zoomOutTicksEditBox = this.addStageTicksEditBox(outRect, this.zoomOutStageTicks, values -> {
            this.zoomOutStageTicks = values;
            this.saveStageTicks();
        });
        LayoutRect inRect = this.getItemRect(ITEM_ZOOM_IN_TICKS_FIELD);
        this.zoomInTicksEditBox = this.addStageTicksEditBox(inRect, this.zoomInStageTicks, values -> {
            this.zoomInStageTicks = values;
            this.saveStageTicks();
        });
    }

    private void initAdvancedThreeWidgets() {
        LayoutRect bodyRect = this.getItemRect(ITEM_BODY_HEIGHT_SLIDER);
        this.bodyHeightSlider = (SingleValueSlider)this.m_142416_((GuiEventListener)new SingleValueSlider(bodyRect.x, bodyRect.y, bodyRect.width, this.getItemComponent(ITEM_BODY_HEIGHT_SLIDER), this.bodyCameraHeight, GtaLikeTeleportConfig.getMinBodyCameraHeight(), GtaLikeTeleportConfig.getMaxBodyCameraHeight(), 0.1, false, " blocks", value -> {
            this.bodyCameraHeight = value;
            GtaLikeTeleportConfig.setBodyCameraHeight(value);
        }));
        LayoutRect glideRect = this.getItemRect(ITEM_BODY_GLIDE_SLIDER);
        this.bodyGlideSlider = (SingleValueSlider)this.m_142416_((GuiEventListener)new SingleValueSlider(glideRect.x, glideRect.y, glideRect.width, this.getItemComponent(ITEM_BODY_GLIDE_SLIDER), this.bodyGlideHeight, GtaLikeTeleportConfig.getMinBodyGlideHeight(), GtaLikeTeleportConfig.getMaxBodyGlideHeight(), 0.1, false, " blocks", value -> {
            this.bodyGlideHeight = value;
            GtaLikeTeleportConfig.setBodyGlideHeight(value);
        }));
        LayoutRect ticksRect = this.getItemRect(ITEM_BODY_GLIDE_TICKS_FIELD);
        this.bodyGlideTicksEditBox = this.addTickEditBox(ticksRect, this.bodyGlideTicks, value -> {
            this.bodyGlideTicks = value;
            GtaLikeTeleportConfig.setBodyGlideTicks(value);
        });
        LayoutRect hideRect = this.getItemRect(ITEM_PLAYER_HIDE_TICKS_FIELD);
        this.playerHideTicksEditBox = this.addLocalPlayerHideTicksEditBox(hideRect, this.localPlayerHideTicks, value -> {
            this.localPlayerHideTicks = value;
            GtaLikeTeleportConfig.setLocalPlayerHideTicks(value);
        });
    }

    private EditBox addTickEditBox(LayoutRect rect, int value, Consumer<Integer> onChanged) {
        EditBox editBox = (EditBox)this.m_142416_((GuiEventListener)new ScaledEditBox(this.f_96547_, rect.x, rect.y, rect.width, rect.height, (Component)Component.m_237113_((String)"ticks"), this.getContentScale()));
        editBox.m_94199_(8);
        this.setEditBoxValue(editBox, Integer.toString(value));
        editBox.m_94151_(text -> {
            if (this.updatingValueWidgets) {
                return;
            }
            Integer parsed = this.parseSingleTick((String)text);
            if (parsed != null) {
                onChanged.accept(parsed);
            }
        });
        return editBox;
    }

    private EditBox addLocalPlayerHideTicksEditBox(LayoutRect rect, int value, Consumer<Integer> onChanged) {
        EditBox editBox = (EditBox)this.m_142416_((GuiEventListener)new ScaledEditBox(this.f_96547_, rect.x, rect.y, rect.width, rect.height, (Component)Component.m_237113_((String)"hide ticks"), this.getContentScale()));
        editBox.m_94199_(4);
        this.setEditBoxValue(editBox, Integer.toString(value));
        editBox.m_94151_(text -> {
            if (this.updatingValueWidgets) {
                return;
            }
            Integer parsed = this.parseLocalPlayerHideTicks((String)text);
            if (parsed != null) {
                onChanged.accept(parsed);
            }
        });
        return editBox;
    }

    private EditBox addStageTicksEditBox(LayoutRect rect, int[] values, Consumer<int[]> onChanged) {
        EditBox editBox = (EditBox)this.m_142416_((GuiEventListener)new ScaledEditBox(this.f_96547_, rect.x, rect.y, rect.width, rect.height, (Component)Component.m_237113_((String)"stage ticks"), this.getContentScale()));
        editBox.m_94199_(32);
        this.setEditBoxValue(editBox, this.formatStageTicks(values));
        editBox.m_94151_(text -> {
            if (this.updatingValueWidgets) {
                return;
            }
            int[] parsed = this.parseStageTicks((String)text);
            if (parsed != null) {
                onChanged.accept(parsed);
            }
        });
        return editBox;
    }

    private void setEditBoxValue(EditBox editBox, String value) {
        if (editBox == null || value.equals(editBox.m_94155_())) {
            return;
        }
        this.updatingValueWidgets = true;
        editBox.m_94144_(value);
        this.updatingValueWidgets = false;
    }

    private Integer parseSingleTick(String text) {
        if (text == null || text.isBlank()) {
            return null;
        }
        try {
            return GtaLikeTeleportConfig.sanitizeStageTicksValue(Integer.parseInt(text.trim()));
        }
        catch (NumberFormatException ignored) {
            return null;
        }
    }

    private Integer parseLocalPlayerHideTicks(String text) {
        if (text == null || text.isBlank()) {
            return null;
        }
        try {
            return GtaLikeTeleportConfig.sanitizeLocalPlayerHideTicks(Integer.parseInt(text.trim()));
        }
        catch (NumberFormatException ignored) {
            return null;
        }
    }

    private int[] parseStageTicks(String text) {
        if (text == null || text.isBlank()) {
            return null;
        }
        String normalized = text.replace('/', ' ').replace(',', ' ').replace(';', ' ');
        String[] tokens = normalized.trim().split("\\s+");
        if (tokens.length != 3) {
            return null;
        }
        int[] values = new int[3];
        try {
            for (int i = 0; i < values.length; ++i) {
                values[i] = Integer.parseInt(tokens[i]);
            }
        }
        catch (NumberFormatException ignored) {
            return null;
        }
        return GtaLikeTeleportConfig.sanitizeStageTicks(values);
    }

    private String formatStageTicks(int[] values) {
        int[] sanitized = GtaLikeTeleportConfig.sanitizeStageTicks(values);
        return sanitized[0] + " / " + sanitized[1] + " / " + sanitized[2];
    }

    private void initPageNavigationWidgets() {
        LayoutRect prevRect = this.getItemRect(ITEM_PREV_PAGE_BUTTON);
        this.prevPageButton = (Button)this.m_142416_((GuiEventListener)Button.m_253074_((Component)Component.m_237119_(), button -> this.switchPage(-1)).m_252987_(prevRect.x, prevRect.y, prevRect.width, prevRect.height).m_253136_());
        LayoutRect nextRect = this.getItemRect(ITEM_NEXT_PAGE_BUTTON);
        this.nextPageButton = (Button)this.m_142416_((GuiEventListener)Button.m_253074_((Component)Component.m_237119_(), button -> this.switchPage(1)).m_252987_(nextRect.x, nextRect.y, nextRect.width, nextRect.height).m_253136_());
    }

    private void initPageTabWidgets() {
        ConfigPage[] pages = ConfigPage.values();
        this.pageTabButtons = new Button[pages.length];
        for (int i = 0; i < pages.length; ++i) {
            ConfigPage page = pages[i];
            LayoutRect rect = this.getItemRect(page.tabItem);
            Button button = (Button)this.m_142416_((GuiEventListener)Button.m_253074_((Component)Component.m_237119_(), clicked -> this.switchToPage(page)).m_252987_(rect.x, rect.y, rect.width, rect.height).m_253136_());
            button.f_93623_ = page != this.currentPage;
            this.pageTabButtons[i] = button;
        }
    }

    public void m_280273_(GuiGraphics context) {
        if (this.f_96541_ == null || this.f_96541_.f_91073_ == null) {
            context.m_280509_(0, 0, this.f_96543_, this.f_96544_, -16777216);
        }
        context.m_280509_(0, 0, this.f_96543_, this.f_96544_, -1728053248);
        LayoutRect outer = GtaLikeTeleportConfigScreen.toOuterRect(this.getContentRect());
        context.m_280509_(outer.x, outer.y, outer.right(), outer.bottom(), 0x66000000);
    }

    public void m_88315_(GuiGraphics context, int mouseX, int mouseY, float tickProgress) {
        this.m_280273_(context);
        super.m_88315_(context, mouseX, mouseY, tickProgress);
        double contentScale = this.getContentScale();
        for (String item : this.getVisibleLayoutItems()) {
            int color;
            if (!this.isManualTextItem(item)) continue;
            LayoutRect rect = this.getItemRect(item);
            int n = color = this.isMutedTextItem(item) ? -6250336 : -1;
            if (this.isCenteredTextItem(item)) {
                GtaLikeTeleportConfigScreen.drawScaledCenteredText(context, this.f_96547_, this.getItemComponent(item), rect.x + rect.width / 2, rect.y, color, contentScale);
                continue;
            }
            GtaLikeTeleportConfigScreen.drawScaledText(context, this.f_96547_, this.getItemComponent(item), rect.x, rect.y, color, contentScale);
        }
        for (String item : this.getVisibleLayoutItems()) {
            if (!this.isManualButtonTextItem(item)) continue;
            this.drawButtonText(context, item);
        }
        this.renderPanelOutline(context);
        if (this.layoutDebugEnabled) {
            LayoutRect panel = this.getContentRect();
            if (this.layoutGridEnabled) {
                this.extractLayoutGuides(context);
            }
            context.m_280430_(this.f_96547_, (Component)Component.m_237115_((String)"gtalike_teleport.config.layout_debug_hint"), panel.x, panel.y + panel.height - 58, -171);
            this.extractLayoutDebugOverlays(context);
        }
    }

    private void renderPanelOutline(GuiGraphics context) {
        LayoutRect outer = GtaLikeTeleportConfigScreen.toOuterRect(this.getContentRect());
        int color = this.layoutDebugEnabled ? -171 : 0x77FFFFFF;
        context.m_280509_(outer.x, outer.y, outer.x + 1, outer.bottom(), color);
        context.m_280509_(outer.right() - 1, outer.y, outer.right(), outer.bottom(), color);
        context.m_280509_(outer.x, outer.bottom() - 1, outer.right(), outer.bottom(), color);
        if (this.layoutDebugEnabled) {
            context.m_280509_(outer.x, outer.y, outer.right(), outer.y + 1, color);
        }
    }

    private static void drawScaledText(GuiGraphics context, Font font, Component text, int x, int y, int color, double scale) {
        if (Math.abs(scale - 1.0) < 0.005) {
            context.m_280430_(font, text, x, y, color);
            return;
        }
        context.m_280168_().m_85836_();
        context.m_280168_().m_252880_((float)x, (float)y, 0.0f);
        context.m_280168_().m_85841_((float)scale, (float)scale, 1.0f);
        context.m_280430_(font, text, 0, 0, color);
        context.m_280168_().m_85849_();
    }

    private static void drawScaledCenteredText(GuiGraphics context, Font font, Component text, int centerX, int y, int color, double scale) {
        if (Math.abs(scale - 1.0) < 0.005) {
            context.m_280653_(font, text, centerX, y, color);
            return;
        }
        int x = centerX - (int)Math.round((double)font.m_92852_((FormattedText)text) * scale / 2.0);
        GtaLikeTeleportConfigScreen.drawScaledText(context, font, text, x, y, color, scale);
    }

    public boolean m_6375_(double mouseX, double mouseY, int button) {
        this.normalizeValueEditorsForClick(mouseX, mouseY);
        if (this.layoutDebugEnabled && this.isDebugControlAt(mouseX, mouseY)) {
            return super.m_6375_(mouseX, mouseY, button);
        }
        if (this.layoutDebugEnabled) {
            LayoutEditAction resizeAction = this.getResizeAction(this.selectedLayoutItem, mouseX, mouseY);
            if (resizeAction != LayoutEditAction.NONE) {
                this.beginLayoutEdit(this.selectedLayoutItem, resizeAction, mouseX, mouseY);
                return true;
            }
            String hitItem = this.findLayoutItemAt(mouseX, mouseY);
            if (hitItem != null) {
                this.selectLayoutItem(hitItem);
                this.beginLayoutEdit(hitItem, LayoutEditAction.MOVE, mouseX, mouseY);
                return true;
            }
        }
        return super.m_6375_(mouseX, mouseY, button);
    }

    public boolean m_7979_(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (this.layoutEditAction != LayoutEditAction.NONE) {
            this.updateLayoutEdit(mouseX, mouseY);
            return true;
        }
        return super.m_7979_(mouseX, mouseY, button, dragX, dragY);
    }

    public boolean m_6348_(double mouseX, double mouseY, int button) {
        if (this.layoutEditAction != LayoutEditAction.NONE) {
            this.finishLayoutEdit();
            return true;
        }
        return super.m_6348_(mouseX, mouseY, button);
    }

    public void m_7379_() {
        this.normalizeValueEditors();
        if (this.f_96541_ != null) {
            this.f_96541_.m_91152_(this.parent);
        }
    }

    private void switchPage(int direction) {
        ConfigPage[] pages = ConfigPage.values();
        int nextIndex = Math.floorMod(this.currentPage.ordinal() + direction, pages.length);
        this.switchToPage(pages[nextIndex]);
    }

    private void switchToPage(ConfigPage nextPage) {
        if (nextPage == this.currentPage) {
            return;
        }
        this.currentPage = nextPage;
        this.layoutEditAction = LayoutEditAction.NONE;
        this.editingLayoutItem = null;
        this.editingRect = null;
        if (!this.isVisibleLayoutItem(this.selectedLayoutItem)) {
            this.selectedLayoutItem = ITEM_PANEL;
        }
        this.m_232761_();
    }

    private void normalizeValueEditorsForClick(double mouseX, double mouseY) {
        if (this.zoomStageGlideTicksEditBox != null && this.zoomStageGlideTicksEditBox.m_93696_() && !this.zoomStageGlideTicksEditBox.m_5953_(mouseX, mouseY)) {
            this.setEditBoxValue(this.zoomStageGlideTicksEditBox, Integer.toString(this.zoomStageGlideTicks));
        }
        if (this.zoomOutTicksEditBox != null && this.zoomOutTicksEditBox.m_93696_() && !this.zoomOutTicksEditBox.m_5953_(mouseX, mouseY)) {
            this.setEditBoxValue(this.zoomOutTicksEditBox, this.formatStageTicks(this.zoomOutStageTicks));
        }
        if (this.zoomInTicksEditBox != null && this.zoomInTicksEditBox.m_93696_() && !this.zoomInTicksEditBox.m_5953_(mouseX, mouseY)) {
            this.setEditBoxValue(this.zoomInTicksEditBox, this.formatStageTicks(this.zoomInStageTicks));
        }
        if (this.bodyGlideTicksEditBox != null && this.bodyGlideTicksEditBox.m_93696_() && !this.bodyGlideTicksEditBox.m_5953_(mouseX, mouseY)) {
            this.setEditBoxValue(this.bodyGlideTicksEditBox, Integer.toString(this.bodyGlideTicks));
        }
        if (this.playerHideTicksEditBox != null && this.playerHideTicksEditBox.m_93696_() && !this.playerHideTicksEditBox.m_5953_(mouseX, mouseY)) {
            this.setEditBoxValue(this.playerHideTicksEditBox, Integer.toString(this.localPlayerHideTicks));
        }
    }

    private void normalizeValueEditors() {
        this.setEditBoxValue(this.zoomStageGlideTicksEditBox, Integer.toString(this.zoomStageGlideTicks));
        this.setEditBoxValue(this.zoomOutTicksEditBox, this.formatStageTicks(this.zoomOutStageTicks));
        this.setEditBoxValue(this.zoomInTicksEditBox, this.formatStageTicks(this.zoomInStageTicks));
        this.setEditBoxValue(this.bodyGlideTicksEditBox, Integer.toString(this.bodyGlideTicks));
        this.setEditBoxValue(this.playerHideTicksEditBox, Integer.toString(this.localPlayerHideTicks));
    }

    private void toggleEffectEnabled() {
        this.effectEnabled = !this.effectEnabled;
        GtaLikeTeleportConfig.setEffectEnabled(this.effectEnabled);
        this.updateGeneralButtons();
    }

    private void toggleMovementAllowed() {
        this.movementAllowed = !this.movementAllowed;
        GtaLikeTeleportConfig.setPlayerFreezeEnabled(!this.movementAllowed);
        this.updateGeneralButtons();
    }

    private void toggleCrossDimensionTravel() {
        this.crossDimensionTravelEnabled = !this.crossDimensionTravelEnabled;
        GtaLikeTeleportConfig.setCrossDimensionTravelEnabled(this.crossDimensionTravelEnabled);
        this.updateGeneralButtons();
    }

    private void toggleWarpPlateTransitions() {
        this.warpPlateTransitionsEnabled = !this.warpPlateTransitionsEnabled;
        GtaLikeTeleportConfig.setWarpPlateTransitionsEnabled(this.warpPlateTransitionsEnabled);
        this.updateOthersButtons();
    }

    private void toggleExternalTeleportTransitions() {
        if (!this.isExternalTeleportToggleAvailable()) {
            this.updateOthersButtons();
            return;
        }
        this.externalTeleportTransitionsEnabled = !this.externalTeleportTransitionsEnabled;
        GtaLikeTeleportConfig.setExternalTeleportTransitionsEnabled(this.externalTeleportTransitionsEnabled);
        this.updateOthersButtons();
    }

    private void toggleFallbackChunkFade() {
        this.fallbackChunkFadeEnabled = !this.fallbackChunkFadeEnabled;
        GtaLikeTeleportConfig.setFallbackChunkFadeEnabled(this.fallbackChunkFadeEnabled);
        this.updateOthersButtons();
    }

    private boolean isExternalTeleportToggleAvailable() {
        return GtaLikeTeleportClientNetworking.isServerSideTeleportAvailable();
    }

    private void updateOthersButtons() {
        if (this.warpPlateToggleButton != null) {
            this.warpPlateToggleButton.m_93666_((Component)Component.m_237119_());
        }
        if (this.externalTeleportToggleButton != null) {
            this.externalTeleportToggleButton.f_93623_ = this.isExternalTeleportToggleAvailable();
            this.externalTeleportToggleButton.m_93666_((Component)Component.m_237119_());
        }
    }

    private void toggleCustomSoundsEnabled() {
        this.customSoundsEnabled = !this.customSoundsEnabled;
        GtaLikeTeleportConfig.setCustomSoundsEnabled(this.customSoundsEnabled);
        this.updateSoundButtons();
    }

    private void updateScaledEditBox(EditBox editBox) {
        if (editBox instanceof ScaledEditBox) {
            ScaledEditBox scaledEditBox = (ScaledEditBox)editBox;
            scaledEditBox.setTextScale(this.getContentScale());
        }
    }

    private void updateSoundButtons() {
        if (this.soundModeToggleButton != null) {
            this.soundModeToggleButton.m_93666_((Component)Component.m_237119_());
        }
    }

    private void updateGeneralButtons() {
        if (this.effectToggleButton != null) {
            this.effectToggleButton.m_93666_((Component)Component.m_237119_());
        }
        if (this.movementToggleButton != null) {
            this.movementToggleButton.m_93666_((Component)Component.m_237119_());
        }
        if (this.crossDimensionTravelToggleButton != null) {
            this.crossDimensionTravelToggleButton.m_93666_((Component)Component.m_237119_());
        }
    }

    private void resetSoundSettings() {
        this.customSoundsEnabled = false;
        this.minecraftSoundVolume = GtaLikeTeleportConfig.getDefaultMinecraftSoundVolume();
        this.customSoundVolume = GtaLikeTeleportConfig.getDefaultCustomSoundVolume();
        GtaLikeTeleportConfig.setCustomSoundsEnabled(this.customSoundsEnabled);
        GtaLikeTeleportConfig.setMinecraftSoundVolume(this.minecraftSoundVolume);
        GtaLikeTeleportConfig.setCustomSoundVolume(this.customSoundVolume);
        this.m_232761_();
    }

    private void resetCurrentPage() {
        if (this.currentPage == ConfigPage.ZOOM) {
            this.resetHeights();
            return;
        }
        if (this.currentPage == ConfigPage.GENERAL) {
            this.effectEnabled = true;
            this.movementAllowed = false;
            this.crossDimensionTravelEnabled = false;
            GtaLikeTeleportConfig.setEffectEnabled(true);
            GtaLikeTeleportConfig.setPlayerFreezeEnabled(true);
            GtaLikeTeleportConfig.setCrossDimensionTravelEnabled(false);
            this.updateGeneralButtons();
            return;
        }
        if (this.currentPage == ConfigPage.ZOOM_STAGE_2) {
            this.zoomOutStageTicks = GtaLikeTeleportConfig.getDefaultStageTicks();
            this.zoomInStageTicks = GtaLikeTeleportConfig.getDefaultStageTicks();
            this.saveStageTicks();
            this.m_232761_();
            return;
        }
        if (this.currentPage == ConfigPage.SOUNDS) {
            this.resetSoundSettings();
            return;
        }
        if (this.currentPage == ConfigPage.OTHERS) {
            this.warpPlateTransitionsEnabled = true;
            this.externalTeleportTransitionsEnabled = true;
            this.fallbackChunkFadeEnabled = false;
            GtaLikeTeleportConfig.setWarpPlateTransitionsEnabled(true);
            GtaLikeTeleportConfig.setExternalTeleportTransitionsEnabled(true);
            GtaLikeTeleportConfig.setFallbackChunkFadeEnabled(false);
            this.updateOthersButtons();
        }
    }

    private void toggleLinked() {
        boolean bl = this.linked = !this.linked;
        if (this.linked) {
            this.zoomInHeights = (double[])this.zoomOutHeights.clone();
        }
        if (!this.isVisibleLayoutItem(this.selectedLayoutItem)) {
            this.selectedLayoutItem = ITEM_PANEL;
        }
        this.saveHeights();
        this.m_232761_();
    }

    private void toggleLayoutDebug() {
        this.layoutDebugEnabled = !this.layoutDebugEnabled;
        GtaLikeTeleportConfig.setConfigLayoutDebugEnabled(this.layoutDebugEnabled);
        this.layoutEditAction = LayoutEditAction.NONE;
        this.editingLayoutItem = null;
        this.editingRect = null;
        if (this.selectedLayoutItem == null) {
            this.selectedLayoutItem = ITEM_PANEL;
        }
        this.m_232761_();
    }

    private void toggleAspectLock() {
        this.layoutAspectLocked = !this.layoutAspectLocked;
        GtaLikeTeleportConfig.setConfigLayoutAspectLocked(this.layoutAspectLocked);
        if (this.aspectButton != null) {
            this.aspectButton.m_93666_(this.getAspectLabel());
        }
    }

    private void toggleGrid() {
        this.layoutGridEnabled = !this.layoutGridEnabled;
        GtaLikeTeleportConfig.setConfigLayoutGridEnabled(this.layoutGridEnabled);
        if (this.gridButton != null) {
            this.gridButton.m_93666_(this.getGridLabel());
        }
    }

    private void toggleSnap() {
        this.layoutSnapEnabled = !this.layoutSnapEnabled;
        GtaLikeTeleportConfig.setConfigLayoutSnapEnabled(this.layoutSnapEnabled);
        if (this.snapButton != null) {
            this.snapButton.m_93666_(this.getSnapLabel());
        }
    }

    private void resetLayout() {
        GtaLikeTeleportConfig.resetConfigLayout();
        GtaLikeTeleportConfig.resetConfigWidgetLayouts();
        this.selectedLayoutItem = ITEM_PANEL;
        this.editingLayoutItem = null;
        this.editingRect = null;
        this.m_232761_();
    }

    private void resetSelectedItemSize() {
        LayoutRect resetRect;
        if (this.selectedLayoutItem == null || !this.isVisibleLayoutItem(this.selectedLayoutItem)) {
            return;
        }
        String item = this.selectedLayoutItem;
        LayoutRect current = this.getEditableRect(item);
        if (ITEM_PANEL.equals(item)) {
            LayoutRect defaults = this.getDefaultContentRect();
            resetRect = this.constrainContentRect(new LayoutRect(current.centerX() - defaults.width / 2, current.centerY() - defaults.height / 2, defaults.width, defaults.height));
        } else {
            LayoutRect defaults = this.getDefaultItemRect(item, this.getContentRect());
            resetRect = this.constrainItemRect(item, new LayoutRect(current.x, current.y, defaults.width, defaults.height));
        }
        this.saveLayoutRect(item, resetRect);
        this.editingLayoutItem = null;
        this.editingRect = null;
        this.layoutEditAction = LayoutEditAction.NONE;
        this.repositionWidgets();
        this.refreshLayoutValueEditor();
    }

    private void applyLayoutValuesFromEditor() {
        LayoutRect rect;
        if (this.layoutValueEditBox == null || this.selectedLayoutItem == null || !this.isVisibleLayoutItem(this.selectedLayoutItem)) {
            return;
        }
        double[] values = this.parseLayoutValues(this.layoutValueEditBox.m_94155_());
        if (values == null) {
            this.refreshLayoutValueEditor();
            return;
        }
        String item = this.selectedLayoutItem;
        if (ITEM_PANEL.equals(item)) {
            rect = this.constrainContentRect(new LayoutRect((int)Math.round(values[0] * (double)this.f_96543_), (int)Math.round(values[1] * (double)this.f_96544_), (int)Math.round(values[2] * (double)this.f_96543_), (int)Math.round(values[3] * (double)this.f_96544_)));
        } else {
            LayoutRect panel = this.getContentRect();
            rect = this.constrainItemRect(item, new LayoutRect(panel.x + (int)Math.round(values[0] * (double)panel.width), panel.y + (int)Math.round(values[1] * (double)panel.height), (int)Math.round(values[2] * (double)panel.width), (int)Math.round(values[3] * (double)panel.height)));
        }
        this.saveLayoutRect(item, rect);
        this.editingLayoutItem = null;
        this.editingRect = null;
        this.layoutEditAction = LayoutEditAction.NONE;
        this.repositionWidgets();
        this.refreshLayoutValueEditor();
    }

    private double[] parseLayoutValues(String text) {
        if (text == null || text.isBlank()) {
            return null;
        }
        double[] values = new double[]{Double.NaN, Double.NaN, Double.NaN, Double.NaN};
        String normalized = text.replace(',', ' ').replace(';', ' ');
        String[] tokens = normalized.trim().split("\\s+");
        int sequentialIndex = 0;
        for (String token : tokens) {
            if (token.isBlank()) continue;
            int equals = token.indexOf(61);
            try {
                if (equals > 0) {
                    String name = token.substring(0, equals).trim().toLowerCase(Locale.ROOT);
                    double value = Double.parseDouble(token.substring(equals + 1).trim());
                    if ("x".equals(name)) {
                        values[0] = value;
                        continue;
                    }
                    if ("y".equals(name)) {
                        values[1] = value;
                        continue;
                    }
                    if ("w".equals(name) || "width".equals(name)) {
                        values[2] = value;
                        continue;
                    }
                    if (!"h".equals(name) && !"height".equals(name)) continue;
                    values[3] = value;
                    continue;
                }
                if (sequentialIndex >= values.length) continue;
                values[sequentialIndex] = Double.parseDouble(token.trim());
                ++sequentialIndex;
            }
            catch (NumberFormatException ignored) {
                return null;
            }
        }
        for (double value : values) {
            if (Double.isFinite(value)) continue;
            return null;
        }
        return values;
    }

    private void saveLayoutRect(String item, LayoutRect rect) {
        if (ITEM_PANEL.equals(item)) {
            if (this.f_96543_ > 0 && this.f_96544_ > 0) {
                GtaLikeTeleportConfig.setConfigLayout((double)rect.x / (double)this.f_96543_, (double)rect.y / (double)this.f_96544_, (double)rect.width / (double)this.f_96543_, (double)rect.height / (double)this.f_96544_, this.f_96543_, this.f_96544_);
            }
            return;
        }
        LayoutRect panel = this.getContentRect();
        if (panel.width > 0 && panel.height > 0) {
            GtaLikeTeleportConfig.setConfigWidgetLayout(item, (double)(rect.x - panel.x) / (double)panel.width, (double)(rect.y - panel.y) / (double)panel.height, (double)rect.width / (double)panel.width, (double)rect.height / (double)panel.height, panel.width, panel.height);
        }
    }

    private void resetHeights() {
        this.zoomOutHeights = GtaLikeTeleportConfig.getDefaultStageHeights();
        this.zoomInHeights = GtaLikeTeleportConfig.getDefaultStageHeights();
        this.saveHeights();
        this.m_232761_();
    }

    private void saveHeights() {
        this.zoomOutHeights = GtaLikeTeleportConfig.sanitizeStageHeights(this.zoomOutHeights);
        this.zoomInHeights = GtaLikeTeleportConfig.sanitizeStageHeights(this.zoomInHeights);
        GtaLikeTeleportConfig.setZoomStageHeights(this.selectedZoomDimension, this.linked, this.zoomOutHeights, this.zoomInHeights);
    }

    private void switchZoomDimension(GtaLikeTeleportConfig.ZoomDimension dimension) {
        if (dimension == null || dimension == this.selectedZoomDimension) {
            return;
        }
        this.selectedZoomDimension = dimension;
        this.loadZoomHeightState();
        this.layoutEditAction = LayoutEditAction.NONE;
        this.editingLayoutItem = null;
        this.editingRect = null;
        if (!this.isVisibleLayoutItem(this.selectedLayoutItem)) {
            this.selectedLayoutItem = ITEM_PANEL;
        }
        this.m_232761_();
    }

    private void saveStageTicks() {
        this.zoomOutStageTicks = GtaLikeTeleportConfig.sanitizeStageTicks(this.zoomOutStageTicks);
        this.zoomInStageTicks = GtaLikeTeleportConfig.sanitizeStageTicks(this.zoomInStageTicks);
        GtaLikeTeleportConfig.setZoomStageTicks(this.zoomOutStageTicks, this.zoomInStageTicks);
    }

    private void updateLinkButton() {
        if (this.linkButton == null) {
            return;
        }
        this.linkButton.setLocked(this.linked);
        this.linkButton.m_257544_(Tooltip.m_257550_((Component)Component.m_237115_((String)(this.linked ? "gtalike_teleport.config.linked.tooltip" : "gtalike_teleport.config.unlinked.tooltip"))));
    }

    private void repositionDimensionButton(DimensionIconButton button, String item, GtaLikeTeleportConfig.ZoomDimension dimension) {
        if (button == null) {
            return;
        }
        LayoutRect rect = this.getItemRect(item);
        GtaLikeTeleportConfigScreen.setWidgetRectangle(button, rect.width, rect.height, rect.x, rect.y);
        button.setSelected(this.selectedZoomDimension == dimension);
    }

    private void updateDimensionButtons() {
        this.repositionDimensionButton(this.overworldDimensionButton, ITEM_DIMENSION_OVERWORLD, GtaLikeTeleportConfig.ZoomDimension.OVERWORLD);
        this.repositionDimensionButton(this.netherDimensionButton, ITEM_DIMENSION_NETHER, GtaLikeTeleportConfig.ZoomDimension.NETHER);
        this.repositionDimensionButton(this.endDimensionButton, ITEM_DIMENSION_END, GtaLikeTeleportConfig.ZoomDimension.END);
    }

    private void repositionWidgets() {
        LayoutRect rect;
        int rightLimit;
        LayoutRect rect2;
        for (String item : this.getVisibleLayoutItems()) {
            this.applyItemText(item);
        }
        if (this.zoomOutSlider != null) {
            rect2 = this.getItemRect(ITEM_ZOOM_OUT_SLIDER);
            GtaLikeTeleportConfigScreen.setWidgetRectangle(this.zoomOutSlider, rect2.width, rect2.height, rect2.x, rect2.y);
        }
        if (this.zoomInSlider != null) {
            if (this.linked) {
                this.zoomInHeights = (double[])this.zoomOutHeights.clone();
                this.zoomInSlider.setValues(this.zoomInHeights);
            }
            this.zoomInSlider.setEditable(!this.linked);
            rect2 = this.getItemRect(ITEM_ZOOM_IN_SLIDER);
            GtaLikeTeleportConfigScreen.setWidgetRectangle(this.zoomInSlider, rect2.width, rect2.height, rect2.x, rect2.y);
        }
        if (this.linkButton != null) {
            rect2 = this.getItemRect(ITEM_LINK_BUTTON);
            GtaLikeTeleportConfigScreen.setWidgetRectangle(this.linkButton, rect2.width, rect2.height, rect2.x, rect2.y);
        }
        this.repositionDimensionButton(this.overworldDimensionButton, ITEM_DIMENSION_OVERWORLD, GtaLikeTeleportConfig.ZoomDimension.OVERWORLD);
        this.repositionDimensionButton(this.netherDimensionButton, ITEM_DIMENSION_NETHER, GtaLikeTeleportConfig.ZoomDimension.NETHER);
        this.repositionDimensionButton(this.endDimensionButton, ITEM_DIMENSION_END, GtaLikeTeleportConfig.ZoomDimension.END);
        if (this.effectToggleButton != null) {
            rect2 = this.getItemRect(ITEM_EFFECT_TOGGLE);
            GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.effectToggleButton, rect2.width, rect2.height, rect2.x, rect2.y);
            this.effectToggleButton.m_93666_((Component)Component.m_237119_());
        }
        if (this.movementToggleButton != null) {
            rect2 = this.getItemRect(ITEM_MOVEMENT_TOGGLE);
            GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.movementToggleButton, rect2.width, rect2.height, rect2.x, rect2.y);
            this.movementToggleButton.m_93666_((Component)Component.m_237119_());
        }
        if (this.crossDimensionTravelToggleButton != null) {
            rect2 = this.getItemRect(ITEM_CROSS_DIMENSION_TRAVEL_TOGGLE);
            GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.crossDimensionTravelToggleButton, rect2.width, rect2.height, rect2.x, rect2.y);
            this.crossDimensionTravelToggleButton.m_93666_((Component)Component.m_237119_());
        }
        if (this.soundModeToggleButton != null) {
            rect2 = this.getItemRect(ITEM_SOUND_MODE_TOGGLE);
            GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.soundModeToggleButton, rect2.width, rect2.height, rect2.x, rect2.y);
            this.soundModeToggleButton.m_93666_((Component)Component.m_237119_());
        }
        if (this.warpPlateToggleButton != null) {
            rect2 = this.getItemRect(ITEM_WARP_PLATE_TOGGLE);
            GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.warpPlateToggleButton, rect2.width, rect2.height, rect2.x, rect2.y);
            this.warpPlateToggleButton.m_93666_((Component)Component.m_237119_());
        }
        if (this.externalTeleportToggleButton != null) {
            rect2 = this.getItemRect(ITEM_EXTERNAL_TELEPORT_TOGGLE);
            GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.externalTeleportToggleButton, rect2.width, rect2.height, rect2.x, rect2.y);
            this.externalTeleportToggleButton.f_93623_ = this.isExternalTeleportToggleAvailable();
            this.externalTeleportToggleButton.m_93666_((Component)Component.m_237119_());
        }
        if (this.minecraftSoundVolumeSlider != null) {
            rect2 = this.getItemRect(ITEM_MINECRAFT_VOLUME_SLIDER);
            GtaLikeTeleportConfigScreen.setWidgetRectangle(this.minecraftSoundVolumeSlider, rect2.width, rect2.height, rect2.x, rect2.y);
            this.minecraftSoundVolumeSlider.setValue(this.minecraftSoundVolume);
        }
        if (this.customSoundVolumeSlider != null) {
            rect2 = this.getItemRect(ITEM_CUSTOM_VOLUME_SLIDER);
            GtaLikeTeleportConfigScreen.setWidgetRectangle(this.customSoundVolumeSlider, rect2.width, rect2.height, rect2.x, rect2.y);
            this.customSoundVolumeSlider.setValue(this.customSoundVolume);
        }
        if (this.zoomStageGlideSlider != null) {
            rect2 = this.getItemRect(ITEM_ZOOM_STAGE_GLIDE_SLIDER);
            GtaLikeTeleportConfigScreen.setWidgetRectangle(this.zoomStageGlideSlider, rect2.width, rect2.height, rect2.x, rect2.y);
            this.zoomStageGlideSlider.setValue(this.zoomStageGlideHeight);
        }
        if (this.zoomStageGlideTicksEditBox != null) {
            rect2 = this.getItemRect(ITEM_ZOOM_STAGE_GLIDE_TICKS_FIELD);
            GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.zoomStageGlideTicksEditBox, rect2.width, rect2.height, rect2.x, rect2.y);
            this.updateScaledEditBox(this.zoomStageGlideTicksEditBox);
            this.zoomStageGlideTicksEditBox.m_94186_(!this.layoutDebugEnabled);
            if (!this.zoomStageGlideTicksEditBox.m_93696_()) {
                this.setEditBoxValue(this.zoomStageGlideTicksEditBox, Integer.toString(this.zoomStageGlideTicks));
            }
        }
        if (this.zoomOutTicksEditBox != null) {
            rect2 = this.getItemRect(ITEM_ZOOM_OUT_TICKS_FIELD);
            GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.zoomOutTicksEditBox, rect2.width, rect2.height, rect2.x, rect2.y);
            this.updateScaledEditBox(this.zoomOutTicksEditBox);
            this.zoomOutTicksEditBox.m_94186_(!this.layoutDebugEnabled);
            if (!this.zoomOutTicksEditBox.m_93696_()) {
                this.setEditBoxValue(this.zoomOutTicksEditBox, this.formatStageTicks(this.zoomOutStageTicks));
            }
        }
        if (this.zoomInTicksEditBox != null) {
            rect2 = this.getItemRect(ITEM_ZOOM_IN_TICKS_FIELD);
            GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.zoomInTicksEditBox, rect2.width, rect2.height, rect2.x, rect2.y);
            this.updateScaledEditBox(this.zoomInTicksEditBox);
            this.zoomInTicksEditBox.m_94186_(!this.layoutDebugEnabled);
            if (!this.zoomInTicksEditBox.m_93696_()) {
                this.setEditBoxValue(this.zoomInTicksEditBox, this.formatStageTicks(this.zoomInStageTicks));
            }
        }
        if (this.bodyHeightSlider != null) {
            rect2 = this.getItemRect(ITEM_BODY_HEIGHT_SLIDER);
            GtaLikeTeleportConfigScreen.setWidgetRectangle(this.bodyHeightSlider, rect2.width, rect2.height, rect2.x, rect2.y);
            this.bodyHeightSlider.setValue(this.bodyCameraHeight);
        }
        if (this.bodyGlideSlider != null) {
            rect2 = this.getItemRect(ITEM_BODY_GLIDE_SLIDER);
            GtaLikeTeleportConfigScreen.setWidgetRectangle(this.bodyGlideSlider, rect2.width, rect2.height, rect2.x, rect2.y);
            this.bodyGlideSlider.setValue(this.bodyGlideHeight);
        }
        if (this.bodyGlideTicksEditBox != null) {
            rect2 = this.getItemRect(ITEM_BODY_GLIDE_TICKS_FIELD);
            GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.bodyGlideTicksEditBox, rect2.width, rect2.height, rect2.x, rect2.y);
            this.updateScaledEditBox(this.bodyGlideTicksEditBox);
            this.bodyGlideTicksEditBox.m_94186_(!this.layoutDebugEnabled);
            if (!this.bodyGlideTicksEditBox.m_93696_()) {
                this.setEditBoxValue(this.bodyGlideTicksEditBox, Integer.toString(this.bodyGlideTicks));
            }
        }
        if (this.playerHideTicksEditBox != null) {
            rect2 = this.getItemRect(ITEM_PLAYER_HIDE_TICKS_FIELD);
            GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.playerHideTicksEditBox, rect2.width, rect2.height, rect2.x, rect2.y);
            this.updateScaledEditBox(this.playerHideTicksEditBox);
            this.playerHideTicksEditBox.m_94186_(!this.layoutDebugEnabled);
            if (!this.playerHideTicksEditBox.m_93696_()) {
                this.setEditBoxValue(this.playerHideTicksEditBox, Integer.toString(this.localPlayerHideTicks));
            }
        }
        if (this.prevPageButton != null) {
            rect2 = this.getItemRect(ITEM_PREV_PAGE_BUTTON);
            GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.prevPageButton, rect2.width, rect2.height, rect2.x, rect2.y);
            this.prevPageButton.m_93666_((Component)Component.m_237119_());
        }
        if (this.nextPageButton != null) {
            rect2 = this.getItemRect(ITEM_NEXT_PAGE_BUTTON);
            GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.nextPageButton, rect2.width, rect2.height, rect2.x, rect2.y);
            this.nextPageButton.m_93666_((Component)Component.m_237119_());
        }
        this.repositionPageTabs();
        LayoutRect panel = this.getContentRect();
        if (this.layoutDebugButton != null) {
            int buttonWidth = 90;
            GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.layoutDebugButton, buttonWidth, 20, Math.max(6, this.f_96543_ - buttonWidth - 6), 6);
            this.layoutDebugButton.m_93666_(this.getLayoutDebugLabel());
        }
        ToolbarCursor cursor = new ToolbarCursor(6, 6);
        int n = rightLimit = this.layoutDebugButton == null ? this.f_96543_ - 6 : this.layoutDebugButton.m_252754_() - 6;
        if (this.aspectButton != null) {
            cursor = this.placeToolbarButton(this.aspectButton, this.getAspectLabel(), cursor, 82, rightLimit);
        }
        if (this.resetLayoutButton != null) {
            cursor = this.placeToolbarButton(this.resetLayoutButton, (Component)Component.m_237115_((String)"gtalike_teleport.config.layout_reset"), cursor, 86, rightLimit);
        }
        if (this.resetItemSizeButton != null) {
            cursor = this.placeToolbarButton(this.resetItemSizeButton, (Component)Component.m_237115_((String)"gtalike_teleport.config.layout_size_reset"), cursor, 88, rightLimit);
        }
        if (this.gridButton != null) {
            cursor = this.placeToolbarButton(this.gridButton, this.getGridLabel(), cursor, 78, rightLimit);
        }
        if (this.snapButton != null) {
            cursor = this.placeToolbarButton(this.snapButton, this.getSnapLabel(), cursor, 78, rightLimit);
        }
        this.repositionLayoutValueEditor(cursor, rightLimit);
        if (this.resetButton != null) {
            rect = this.getItemRect(ITEM_RESET_BUTTON);
            GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.resetButton, rect.width, rect.height, rect.x, rect.y);
        }
        if (this.doneButton != null) {
            rect = this.getItemRect(ITEM_DONE_BUTTON);
            GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.doneButton, rect.width, rect.height, rect.x, rect.y);
        }
        this.repositionTextEditor();
    }

    private void repositionPageTabs() {
        if (this.pageTabButtons == null || this.pageTabButtons.length == 0) {
            return;
        }
        ConfigPage[] pages = ConfigPage.values();
        for (int i = 0; i < this.pageTabButtons.length && i < pages.length; ++i) {
            Button button = this.pageTabButtons[i];
            if (button == null) continue;
            LayoutRect rect = this.getItemRect(pages[i].tabItem);
            GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)button, rect.width, rect.height, rect.x, rect.y);
            button.m_93666_((Component)Component.m_237119_());
            button.f_93623_ = pages[i] != this.currentPage;
        }
    }

    private LayoutRect[] getPageTabRects() {
        ConfigPage[] pages = ConfigPage.values();
        LayoutRect[] rects = new LayoutRect[pages.length];
        LayoutRect outer = GtaLikeTeleportConfigScreen.toOuterRect(this.getContentRect());
        int totalWidth = Math.max(pages.length, outer.width);
        double scale = this.getContentScale();
        int tabHeight = Math.max(16, (int)Math.round(20.0 * scale));
        int y = Math.max(2, outer.y - tabHeight - Math.max(2, (int)Math.round(2.0 * scale)));
        int baseWidth = Math.max(24, totalWidth / pages.length);
        int remainder = Math.max(0, totalWidth - baseWidth * pages.length);
        int x = outer.x;
        for (int i = 0; i < pages.length; ++i) {
            int width = baseWidth + (i < remainder ? 1 : 0);
            rects[i] = new LayoutRect(x, y, width, tabHeight);
            x += width;
        }
        return rects;
    }

    private LayoutRect getDefaultPageTabRect(String item) {
        ConfigPage[] pages = ConfigPage.values();
        LayoutRect[] rects = this.getPageTabRects();
        for (int i = 0; i < pages.length && i < rects.length; ++i) {
            if (!pages[i].tabItem.equals(item)) continue;
            return rects[i];
        }
        return new LayoutRect(0, 0, 24, 20);
    }

    private boolean isPageTabItem(String item) {
        for (ConfigPage page : ConfigPage.values()) {
            if (!page.tabItem.equals(item)) continue;
            return true;
        }
        return false;
    }

    private ToolbarCursor placeToolbarButton(Button button, Component label, ToolbarCursor cursor, int width, int rightLimit) {
        int clampedRightLimit = Math.max(width + 6, rightLimit);
        int buttonX = cursor.x;
        int buttonY = cursor.y;
        if (buttonX + width > clampedRightLimit) {
            buttonX = 6;
            buttonY += 22;
        }
        GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)button, width, 20, buttonX, buttonY);
        button.m_93666_(label);
        return new ToolbarCursor(buttonX + width + 6, buttonY);
    }

    private void beginLayoutEdit(String item, LayoutEditAction action, double mouseX, double mouseY) {
        this.selectedLayoutItem = item;
        this.editingLayoutItem = item;
        this.layoutEditAction = action;
        this.editingRect = this.editStartRect = this.getEditableRect(item);
        this.editStartMouseX = mouseX;
        this.editStartMouseY = mouseY;
        this.refreshTextEditor();
        this.refreshLayoutValueEditor();
    }

    private void updateLayoutEdit(double mouseX, double mouseY) {
        if (this.editStartRect == null || this.editingLayoutItem == null) {
            return;
        }
        int dx = (int)Math.round(mouseX - this.editStartMouseX);
        int dy = (int)Math.round(mouseY - this.editStartMouseY);
        int x = this.editStartRect.x;
        int y = this.editStartRect.y;
        int itemWidth = this.editStartRect.width;
        int itemHeight = this.editStartRect.height;
        if (this.layoutEditAction == LayoutEditAction.MOVE) {
            x += dx;
            y += dy;
        } else if (this.layoutEditAction == LayoutEditAction.RESIZE_RIGHT) {
            itemWidth += dx;
        } else if (this.layoutEditAction == LayoutEditAction.RESIZE_BOTTOM) {
            itemHeight += dy;
        } else if (this.layoutEditAction == LayoutEditAction.RESIZE_CORNER) {
            itemWidth += dx;
            itemHeight += dy;
            if (this.layoutAspectLocked && this.editStartRect.height > 0) {
                double aspect = (double)this.editStartRect.width / (double)this.editStartRect.height;
                if (Math.abs(dx) >= Math.abs(dy)) {
                    itemHeight = (int)Math.round((double)itemWidth / aspect);
                } else {
                    itemWidth = (int)Math.round((double)itemHeight * aspect);
                }
            }
        }
        LayoutRect raw = new LayoutRect(x, y, itemWidth, itemHeight);
        LayoutRect constrained = ITEM_PANEL.equals(this.editingLayoutItem) ? this.constrainContentRect(raw) : this.constrainItemRect(this.editingLayoutItem, raw);
        this.editingRect = this.applyLayoutSnap(this.editingLayoutItem, constrained);
        this.repositionWidgets();
        this.refreshLayoutValueEditor();
    }

    private void finishLayoutEdit() {
        if (this.editingLayoutItem != null) {
            this.saveLayoutRect(this.editingLayoutItem, this.getEditableRect(this.editingLayoutItem));
        }
        this.layoutEditAction = LayoutEditAction.NONE;
        this.editingLayoutItem = null;
        this.editStartRect = null;
        this.editingRect = null;
        this.repositionWidgets();
        this.refreshLayoutValueEditor();
    }

    private LayoutRect applyLayoutSnap(String item, LayoutRect rect) {
        if (!this.layoutSnapEnabled || item == null || this.layoutEditAction == LayoutEditAction.NONE) {
            return rect;
        }
        List<Integer> horizontalTargets = this.getHorizontalSnapTargets(item);
        List<Integer> verticalTargets = this.getVerticalSnapTargets(item);
        LayoutRect snapped = rect;
        if (this.layoutEditAction == LayoutEditAction.MOVE) {
            int offsetX = this.findSnapOffset(new int[]{rect.x, rect.centerX(), rect.right()}, horizontalTargets);
            int offsetY = this.findSnapOffset(new int[]{rect.y, rect.centerY(), rect.bottom()}, verticalTargets);
            snapped = new LayoutRect(rect.x + offsetX, rect.y + offsetY, rect.width, rect.height);
        } else {
            int x = rect.x;
            int y = rect.y;
            int itemWidth = rect.width;
            int itemHeight = rect.height;
            if (this.layoutEditAction == LayoutEditAction.RESIZE_RIGHT || this.layoutEditAction == LayoutEditAction.RESIZE_CORNER) {
                int rightOffset = this.findSnapOffset(new int[]{rect.right()}, horizontalTargets);
                int widthOffset = this.findSnapOffset(new int[]{rect.width}, this.getWidthSnapTargets(item));
                itemWidth = rect.width + this.chooseSnapOffset(rightOffset, widthOffset);
            }
            if (this.layoutEditAction == LayoutEditAction.RESIZE_BOTTOM || this.layoutEditAction == LayoutEditAction.RESIZE_CORNER) {
                int bottomOffset = this.findSnapOffset(new int[]{rect.bottom()}, verticalTargets);
                int heightOffset = this.findSnapOffset(new int[]{rect.height}, this.getHeightSnapTargets(item));
                itemHeight = rect.height + this.chooseSnapOffset(bottomOffset, heightOffset);
            }
            snapped = new LayoutRect(x, y, itemWidth, itemHeight);
        }
        return ITEM_PANEL.equals(item) ? this.constrainContentRect(snapped) : this.constrainItemRect(item, snapped);
    }

    private int chooseSnapOffset(int edgeOffset, int sizeOffset) {
        if (edgeOffset == 0) {
            return sizeOffset;
        }
        if (sizeOffset == 0) {
            return edgeOffset;
        }
        return Math.abs(sizeOffset) < Math.abs(edgeOffset) ? sizeOffset : edgeOffset;
    }

    private List<Integer> getWidthSnapTargets(String item) {
        ArrayList<Integer> targets = new ArrayList<Integer>();
        LayoutRect panel = this.getContentRect();
        targets.add(panel.width);
        targets.add(Math.max(1, panel.width / 2));
        for (String visibleItem : this.getVisibleLayoutItems()) {
            if (visibleItem.equals(item) || ITEM_PANEL.equals(visibleItem)) continue;
            LayoutRect rect = this.getSelectableRect(visibleItem);
            targets.add(rect.width);
        }
        return targets;
    }

    private List<Integer> getHeightSnapTargets(String item) {
        ArrayList<Integer> targets = new ArrayList<Integer>();
        LayoutRect panel = this.getContentRect();
        targets.add(panel.height);
        targets.add(Math.max(1, panel.height / 2));
        for (String visibleItem : this.getVisibleLayoutItems()) {
            if (visibleItem.equals(item) || ITEM_PANEL.equals(visibleItem)) continue;
            LayoutRect rect = this.getSelectableRect(visibleItem);
            targets.add(rect.height);
        }
        return targets;
    }

    private List<Integer> getHorizontalSnapTargets(String item) {
        ArrayList<Integer> targets = new ArrayList<Integer>();
        targets.add(0);
        targets.add(this.f_96543_ / 2);
        targets.add(this.f_96543_);
        if (!ITEM_PANEL.equals(item)) {
            LayoutRect panel = this.getContentRect();
            GtaLikeTeleportConfigScreen.addHorizontalTargets(targets, panel);
            for (String visibleItem : this.getVisibleLayoutItems()) {
                if (ITEM_PANEL.equals(visibleItem) || visibleItem.equals(item)) continue;
                GtaLikeTeleportConfigScreen.addHorizontalTargets(targets, this.getSelectableRect(visibleItem));
            }
        }
        return targets;
    }

    private List<Integer> getVerticalSnapTargets(String item) {
        ArrayList<Integer> targets = new ArrayList<Integer>();
        targets.add(0);
        targets.add(this.f_96544_ / 2);
        targets.add(this.f_96544_);
        if (!ITEM_PANEL.equals(item)) {
            LayoutRect panel = this.getContentRect();
            GtaLikeTeleportConfigScreen.addVerticalTargets(targets, panel);
            for (String visibleItem : this.getVisibleLayoutItems()) {
                if (ITEM_PANEL.equals(visibleItem) || visibleItem.equals(item)) continue;
                GtaLikeTeleportConfigScreen.addVerticalTargets(targets, this.getSelectableRect(visibleItem));
            }
        }
        return targets;
    }

    private static void addHorizontalTargets(List<Integer> targets, LayoutRect rect) {
        targets.add(rect.x);
        targets.add(rect.centerX());
        targets.add(rect.right());
    }

    private static void addVerticalTargets(List<Integer> targets, LayoutRect rect) {
        targets.add(rect.y);
        targets.add(rect.centerY());
        targets.add(rect.bottom());
    }

    private int findSnapOffset(int[] anchors, List<Integer> targets) {
        int bestDistance = 6;
        int bestOffset = 0;
        for (int anchor : anchors) {
            int target;
            int distance;
            for (int target2 : targets) {
                int distance2 = Math.abs(target2 - anchor);
                if (distance2 >= bestDistance) continue;
                bestDistance = distance2;
                bestOffset = target2 - anchor;
            }
            if (!this.layoutGridEnabled || (distance = Math.abs((target = GtaLikeTeleportConfigScreen.roundToGrid(anchor, 8)) - anchor)) >= bestDistance) continue;
            bestDistance = distance;
            bestOffset = target - anchor;
        }
        return bestDistance <= 5 ? bestOffset : 0;
    }

    private static int roundToGrid(int value, int grid) {
        return Math.round((float)value / (float)grid) * grid;
    }

    private LayoutEditAction getResizeAction(String item, double mouseX, double mouseY) {
        boolean nearCorner;
        if (item == null || !this.isVisibleLayoutItem(item)) {
            return LayoutEditAction.NONE;
        }
        LayoutRect rect = this.getSelectableRect(item);
        boolean nearRight = Math.abs(mouseX - (double)rect.right()) <= 10.0 && mouseY >= (double)rect.y && mouseY <= (double)rect.bottom();
        boolean nearBottom = Math.abs(mouseY - (double)rect.bottom()) <= 10.0 && mouseX >= (double)rect.x && mouseX <= (double)rect.right();
        boolean bl = nearCorner = mouseX >= (double)(rect.right() - 20) && mouseX <= (double)(rect.right() + 10) && mouseY >= (double)(rect.bottom() - 20) && mouseY <= (double)(rect.bottom() + 10);
        if (nearCorner) {
            return LayoutEditAction.RESIZE_CORNER;
        }
        if (nearRight) {
            return LayoutEditAction.RESIZE_RIGHT;
        }
        if (nearBottom) {
            return LayoutEditAction.RESIZE_BOTTOM;
        }
        return LayoutEditAction.NONE;
    }

    private void extractLayoutGuides(GuiGraphics context) {
        int startY;
        int startX;
        LayoutRect panel = this.getContentRect();
        for (int x = startX = panel.x + Math.floorMod(-panel.x, 8); x <= panel.right(); x += 8) {
            GtaLikeTeleportConfigScreen.drawVerticalGuide(context, x, panel.y, panel.bottom(), 0x22FFFFFF);
        }
        for (int y = startY = panel.y + Math.floorMod(-panel.y, 8); y <= panel.bottom(); y += 8) {
            GtaLikeTeleportConfigScreen.drawHorizontalGuide(context, y, panel.x, panel.right(), 0x22FFFFFF);
        }
        GtaLikeTeleportConfigScreen.drawVerticalGuide(context, this.f_96543_ / 2, 0, this.f_96544_, 2007682303);
        GtaLikeTeleportConfigScreen.drawHorizontalGuide(context, this.f_96544_ / 2, 0, this.f_96543_, 2007682303);
        GtaLikeTeleportConfigScreen.drawVerticalGuide(context, panel.centerX(), panel.y, panel.bottom(), 0x66FFFFFF);
        GtaLikeTeleportConfigScreen.drawHorizontalGuide(context, panel.centerY(), panel.x, panel.right(), 0x66FFFFFF);
        if (this.selectedLayoutItem != null && this.isVisibleLayoutItem(this.selectedLayoutItem)) {
            LayoutRect selected = this.getSelectableRect(this.selectedLayoutItem);
            GtaLikeTeleportConfigScreen.drawVerticalGuide(context, selected.centerX(), panel.y, panel.bottom(), -1996488875);
            GtaLikeTeleportConfigScreen.drawHorizontalGuide(context, selected.centerY(), panel.x, panel.right(), -1996488875);
        }
    }

    private static void drawVerticalGuide(GuiGraphics context, int x, int y1, int y2, int color) {
        context.m_280509_(x, y1, x + 1, y2, color);
    }

    private static void drawHorizontalGuide(GuiGraphics context, int y, int x1, int x2, int color) {
        context.m_280509_(x1, y, x2, y + 1, color);
    }

    private void extractLayoutDebugOverlays(GuiGraphics context) {
        for (String item : this.getVisibleLayoutItems()) {
            LayoutRect rect = this.getSelectableRect(item);
            boolean selected = item.equals(this.selectedLayoutItem);
            int color = selected ? -171 : -1996488875;
            context.m_280637_(rect.x, rect.y, rect.width, rect.height, color);
        }
        if (this.selectedLayoutItem != null && this.isVisibleLayoutItem(this.selectedLayoutItem)) {
            LayoutRect rect = this.getSelectableRect(this.selectedLayoutItem);
            context.m_280509_(rect.right() - 2, rect.y, rect.right() + 2, rect.bottom(), -855638187);
            context.m_280509_(rect.x, rect.bottom() - 2, rect.right(), rect.bottom() + 2, -855638187);
            context.m_280509_(rect.right() - 9, rect.bottom() - 9, rect.right() + 2, rect.bottom() + 2, -570425515);
            context.m_280637_(rect.right() - 9, rect.bottom() - 9, 11, 11, -1);
        }
    }

    private String findLayoutItemAt(double mouseX, double mouseY) {
        List<String> items = this.getVisibleLayoutItems();
        for (int i = items.size() - 1; i >= 0; --i) {
            String item = items.get(i);
            if (!this.getSelectableRect(item).contains(mouseX, mouseY)) continue;
            return item;
        }
        return null;
    }

    private void selectLayoutItem(String item) {
        if (item != null && !item.equals(this.selectedLayoutItem)) {
            this.selectedLayoutItem = item;
            this.refreshTextEditor();
            this.refreshLayoutValueEditor();
            this.repositionWidgets();
        }
    }

    private boolean isDebugControlAt(double mouseX, double mouseY) {
        return GtaLikeTeleportConfigScreen.isWidgetAt((AbstractWidget)this.layoutDebugButton, mouseX, mouseY) || GtaLikeTeleportConfigScreen.isWidgetAt((AbstractWidget)this.aspectButton, mouseX, mouseY) || GtaLikeTeleportConfigScreen.isWidgetAt((AbstractWidget)this.resetLayoutButton, mouseX, mouseY) || GtaLikeTeleportConfigScreen.isWidgetAt((AbstractWidget)this.resetItemSizeButton, mouseX, mouseY) || GtaLikeTeleportConfigScreen.isWidgetAt((AbstractWidget)this.gridButton, mouseX, mouseY) || GtaLikeTeleportConfigScreen.isWidgetAt((AbstractWidget)this.snapButton, mouseX, mouseY) || GtaLikeTeleportConfigScreen.isWidgetAt((AbstractWidget)this.prevPageButton, mouseX, mouseY) || GtaLikeTeleportConfigScreen.isWidgetAt((AbstractWidget)this.nextPageButton, mouseX, mouseY) || this.layoutValueEditorVisible && GtaLikeTeleportConfigScreen.isWidgetAt((AbstractWidget)this.layoutValueEditBox, mouseX, mouseY) || this.layoutValueEditorVisible && GtaLikeTeleportConfigScreen.isWidgetAt((AbstractWidget)this.applyLayoutValueButton, mouseX, mouseY) || this.textEditorVisible && GtaLikeTeleportConfigScreen.isWidgetAt((AbstractWidget)this.textEditBox, mouseX, mouseY);
    }

    private boolean isPageTabAt(double mouseX, double mouseY) {
        if (this.pageTabButtons == null) {
            return false;
        }
        for (Button button : this.pageTabButtons) {
            if (!GtaLikeTeleportConfigScreen.isWidgetAt((AbstractWidget)button, mouseX, mouseY)) continue;
            return true;
        }
        return false;
    }

    private static boolean isWidgetAt(AbstractWidget widget, double mouseX, double mouseY) {
        return widget != null && widget.m_5953_(mouseX, mouseY);
    }

    private static void setWidgetRectangle(AbstractWidget widget, int width, int height, int x, int y) {
        if (widget == null) {
            return;
        }
        widget.m_252865_(x);
        widget.m_253211_(y);
        widget.m_93674_(width);
        GtaLikeTeleportConfigScreen.setWidgetHeight(widget, height);
    }

    private static void setWidgetHeight(AbstractWidget widget, int height) {
        try {
            Field field = AbstractWidget.class.getDeclaredField("height");
            field.setAccessible(true);
            field.setInt(widget, height);
        }
        catch (ReflectiveOperationException reflectiveOperationException) {
            // empty catch block
        }
    }

    private void refreshLayoutValueEditor() {
        boolean visible;
        if (this.layoutValueEditBox == null) {
            return;
        }
        this.layoutValueEditorVisible = visible = this.layoutDebugEnabled && this.selectedLayoutItem != null && this.isVisibleLayoutItem(this.selectedLayoutItem);
        this.layoutValueEditBox.m_94194_(visible);
        this.layoutValueEditBox.m_94186_(visible);
        if (!visible) {
            this.layoutValueEditBox.m_93692_(false);
            if (this.applyLayoutValueButton != null) {
                GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.applyLayoutValueButton, 1, 1, -1000, -1000);
            }
            return;
        }
        this.updatingLayoutValueEditor = true;
        this.layoutValueEditBox.m_94144_(this.formatCurrentLayoutValues());
        this.updatingLayoutValueEditor = false;
    }

    private void repositionLayoutValueEditor(ToolbarCursor cursor, int rightLimit) {
        boolean visible;
        if (this.layoutValueEditBox == null) {
            return;
        }
        this.layoutValueEditorVisible = visible = this.layoutDebugEnabled && this.selectedLayoutItem != null && this.isVisibleLayoutItem(this.selectedLayoutItem);
        this.layoutValueEditBox.m_94194_(visible);
        this.layoutValueEditBox.m_94186_(visible);
        if (!visible) {
            if (this.applyLayoutValueButton != null) {
                GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.applyLayoutValueButton, 1, 1, -1000, -1000);
            }
            return;
        }
        int editorX = 6;
        int editorY = cursor.y + 22;
        int applyWidth = 64;
        int availableWidth = Math.max(150, rightLimit - editorX);
        int editorWidth = Math.min(300, Math.max(140, availableWidth - applyWidth - 6));
        if (editorX + editorWidth + 6 + applyWidth > rightLimit) {
            editorWidth = Math.min(300, Math.max(140, availableWidth));
            GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.layoutValueEditBox, editorWidth, 20, editorX, editorY);
            if (this.applyLayoutValueButton != null) {
                GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.applyLayoutValueButton, applyWidth, 20, editorX, editorY + 22);
            }
            return;
        }
        GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.layoutValueEditBox, editorWidth, 20, editorX, editorY);
        if (this.applyLayoutValueButton != null) {
            GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.applyLayoutValueButton, applyWidth, 20, editorX + editorWidth + 6, editorY);
        }
    }

    private String formatCurrentLayoutValues() {
        double h;
        double w;
        double y;
        double x;
        if (this.selectedLayoutItem == null || !this.isVisibleLayoutItem(this.selectedLayoutItem)) {
            return "x=0 y=0 w=0 h=0";
        }
        LayoutRect rect = this.getEditableRect(this.selectedLayoutItem);
        if (ITEM_PANEL.equals(this.selectedLayoutItem)) {
            x = this.f_96543_ <= 0 ? 0.0 : (double)rect.x / (double)this.f_96543_;
            y = this.f_96544_ <= 0 ? 0.0 : (double)rect.y / (double)this.f_96544_;
            w = this.f_96543_ <= 0 ? 0.0 : (double)rect.width / (double)this.f_96543_;
            h = this.f_96544_ <= 0 ? 0.0 : (double)rect.height / (double)this.f_96544_;
        } else {
            LayoutRect panel = this.getContentRect();
            x = panel.width <= 0 ? 0.0 : (double)(rect.x - panel.x) / (double)panel.width;
            y = panel.height <= 0 ? 0.0 : (double)(rect.y - panel.y) / (double)panel.height;
            w = panel.width <= 0 ? 0.0 : (double)rect.width / (double)panel.width;
            h = panel.height <= 0 ? 0.0 : (double)rect.height / (double)panel.height;
        }
        return String.format(Locale.ROOT, "x=%.3f y=%.3f w=%.3f h=%.3f", x, y, w, h);
    }

    private void refreshTextEditor() {
        boolean visible;
        if (this.textEditBox == null) {
            return;
        }
        this.textEditorVisible = visible = this.layoutDebugEnabled && this.itemSupportsText(this.selectedLayoutItem) && this.isVisibleLayoutItem(this.selectedLayoutItem);
        this.textEditBox.m_94194_(visible);
        this.textEditBox.m_94186_(visible);
        if (!visible) {
            this.textEditBox.m_93692_(false);
            return;
        }
        this.updatingTextEditor = true;
        this.textEditBox.m_94144_(this.getItemText(this.selectedLayoutItem));
        this.updatingTextEditor = false;
        this.repositionTextEditor();
    }

    private void repositionTextEditor() {
        boolean visible;
        if (this.textEditBox == null) {
            return;
        }
        this.textEditorVisible = visible = this.layoutDebugEnabled && this.itemSupportsText(this.selectedLayoutItem) && this.isVisibleLayoutItem(this.selectedLayoutItem);
        this.textEditBox.m_94194_(visible);
        this.textEditBox.m_94186_(visible);
        if (!visible) {
            return;
        }
        LayoutRect anchor = this.getSelectableRect(this.selectedLayoutItem);
        int editorWidth = Math.min(360, Math.max(180, anchor.width + 80));
        int editorHeight = 20;
        int editorX = GtaLikeTeleportConfigScreen.clamp(anchor.x, 8, Math.max(8, this.f_96543_ - editorWidth - 8));
        int editorY = anchor.bottom() + 6;
        if (editorY + editorHeight > this.f_96544_ - 8) {
            editorY = anchor.y - editorHeight - 6;
        }
        editorY = GtaLikeTeleportConfigScreen.clamp(editorY, 8, Math.max(8, this.f_96544_ - editorHeight - 8));
        GtaLikeTeleportConfigScreen.setWidgetRectangle((AbstractWidget)this.textEditBox, editorWidth, editorHeight, editorX, editorY);
    }

    private void applyItemText(String item) {
        Component component = this.getItemComponent(item);
        if (ITEM_LINKED_SLIDER.equals(item) && this.linkedSlider != null) {
            this.linkedSlider.m_93666_(component);
        } else if (ITEM_ZOOM_OUT_SLIDER.equals(item) && this.zoomOutSlider != null) {
            this.zoomOutSlider.m_93666_(component);
        } else if (ITEM_ZOOM_IN_SLIDER.equals(item) && this.zoomInSlider != null) {
            this.zoomInSlider.m_93666_(component);
        } else if (ITEM_ZOOM_STAGE_GLIDE_SLIDER.equals(item) && this.zoomStageGlideSlider != null) {
            this.zoomStageGlideSlider.m_93666_(component);
        } else if (ITEM_BODY_HEIGHT_SLIDER.equals(item) && this.bodyHeightSlider != null) {
            this.bodyHeightSlider.m_93666_(component);
        } else if (ITEM_BODY_GLIDE_SLIDER.equals(item) && this.bodyGlideSlider != null) {
            this.bodyGlideSlider.m_93666_(component);
        } else if (ITEM_EFFECT_TOGGLE.equals(item) && this.effectToggleButton != null) {
            this.effectToggleButton.m_93666_((Component)Component.m_237119_());
        } else if (ITEM_MOVEMENT_TOGGLE.equals(item) && this.movementToggleButton != null) {
            this.movementToggleButton.m_93666_((Component)Component.m_237119_());
        } else if (ITEM_CROSS_DIMENSION_TRAVEL_TOGGLE.equals(item) && this.crossDimensionTravelToggleButton != null) {
            this.crossDimensionTravelToggleButton.m_93666_((Component)Component.m_237119_());
        } else if (ITEM_SOUND_MODE_TOGGLE.equals(item) && this.soundModeToggleButton != null) {
            this.soundModeToggleButton.m_93666_((Component)Component.m_237119_());
        } else if (ITEM_WARP_PLATE_TOGGLE.equals(item) && this.warpPlateToggleButton != null) {
            this.warpPlateToggleButton.m_93666_((Component)Component.m_237119_());
        } else if (ITEM_EXTERNAL_TELEPORT_TOGGLE.equals(item) && this.externalTeleportToggleButton != null) {
            this.externalTeleportToggleButton.m_93666_((Component)Component.m_237119_());
        } else if (ITEM_MINECRAFT_VOLUME_SLIDER.equals(item) && this.minecraftSoundVolumeSlider != null) {
            this.minecraftSoundVolumeSlider.m_93666_(component);
        } else if (ITEM_CUSTOM_VOLUME_SLIDER.equals(item) && this.customSoundVolumeSlider != null) {
            this.customSoundVolumeSlider.m_93666_(component);
        } else if (this.isPageTabItem(item)) {
            this.applyPageTabText(item);
        } else if (ITEM_PREV_PAGE_BUTTON.equals(item) && this.prevPageButton != null) {
            this.prevPageButton.m_93666_((Component)Component.m_237119_());
        } else if (ITEM_NEXT_PAGE_BUTTON.equals(item) && this.nextPageButton != null) {
            this.nextPageButton.m_93666_((Component)Component.m_237119_());
        } else if (ITEM_RESET_BUTTON.equals(item) && this.resetButton != null) {
            this.resetButton.m_93666_((Component)Component.m_237119_());
        } else if (ITEM_DONE_BUTTON.equals(item) && this.doneButton != null) {
            this.doneButton.m_93666_((Component)Component.m_237119_());
        }
    }

    private void applyPageTabText(String item) {
        if (this.pageTabButtons == null) {
            return;
        }
        ConfigPage[] pages = ConfigPage.values();
        for (int i = 0; i < pages.length && i < this.pageTabButtons.length; ++i) {
            if (!pages[i].tabItem.equals(item) || this.pageTabButtons[i] == null) continue;
            this.pageTabButtons[i].m_93666_((Component)Component.m_237119_());
            return;
        }
    }

    private boolean itemSupportsText(String item) {
        return this.isPageTabItem(item) || ITEM_TITLE.equals(item) || ITEM_DESCRIPTION.equals(item) || ITEM_GENERAL_TITLE.equals(item) || ITEM_GENERAL_DESCRIPTION.equals(item) || ITEM_STATUS_LINKED.equals(item) || ITEM_STATUS_UNLINKED.equals(item) || ITEM_EFFECT_LABEL.equals(item) || ITEM_MOVEMENT_LABEL.equals(item) || ITEM_CROSS_DIMENSION_TRAVEL_LABEL.equals(item) || ITEM_CROSS_DIMENSION_TRAVEL_TOGGLE.equals(item) || ITEM_ADVANCED1_TITLE.equals(item) || ITEM_ADVANCED1_DESCRIPTION.equals(item) || ITEM_ADVANCED2_TITLE.equals(item) || ITEM_ADVANCED2_DESCRIPTION.equals(item) || ITEM_ADVANCED3_TITLE.equals(item) || ITEM_ADVANCED3_DESCRIPTION.equals(item) || ITEM_SOUNDS_TITLE.equals(item) || ITEM_SOUNDS_DESCRIPTION.equals(item) || ITEM_SOUND_MODE_LABEL.equals(item) || ITEM_SOUND_MODE_TOGGLE.equals(item) || ITEM_OTHERS_TITLE.equals(item) || ITEM_OTHERS_DESCRIPTION.equals(item) || ITEM_WARP_PLATE_LABEL.equals(item) || ITEM_WARP_PLATE_TOGGLE.equals(item) || ITEM_EXTERNAL_TELEPORT_LABEL.equals(item) || ITEM_EXTERNAL_TELEPORT_TOGGLE.equals(item) || ITEM_MINECRAFT_VOLUME_SLIDER.equals(item) || ITEM_CUSTOM_VOLUME_SLIDER.equals(item) || ITEM_ZOOM_STAGE_GLIDE_TICKS_LABEL.equals(item) || ITEM_ZOOM_OUT_TICKS_LABEL.equals(item) || ITEM_ZOOM_IN_TICKS_LABEL.equals(item) || ITEM_BODY_GLIDE_TICKS_LABEL.equals(item) || ITEM_PLAYER_HIDE_LABEL.equals(item) || ITEM_LINKED_SLIDER.equals(item) || ITEM_ZOOM_OUT_SLIDER.equals(item) || ITEM_ZOOM_IN_SLIDER.equals(item) || ITEM_ZOOM_STAGE_GLIDE_SLIDER.equals(item) || ITEM_BODY_HEIGHT_SLIDER.equals(item) || ITEM_BODY_GLIDE_SLIDER.equals(item) || ITEM_RESET_BUTTON.equals(item) || ITEM_DONE_BUTTON.equals(item);
    }

    private boolean isManualTextItem(String item) {
        return ITEM_TITLE.equals(item) || ITEM_DESCRIPTION.equals(item) || ITEM_GENERAL_TITLE.equals(item) || ITEM_GENERAL_DESCRIPTION.equals(item) || ITEM_STATUS_LINKED.equals(item) || ITEM_STATUS_UNLINKED.equals(item) || ITEM_EFFECT_LABEL.equals(item) || ITEM_MOVEMENT_LABEL.equals(item) || ITEM_CROSS_DIMENSION_TRAVEL_LABEL.equals(item) || ITEM_ADVANCED1_TITLE.equals(item) || ITEM_ADVANCED1_DESCRIPTION.equals(item) || ITEM_ADVANCED2_TITLE.equals(item) || ITEM_ADVANCED2_DESCRIPTION.equals(item) || ITEM_ADVANCED3_TITLE.equals(item) || ITEM_ADVANCED3_DESCRIPTION.equals(item) || ITEM_SOUNDS_TITLE.equals(item) || ITEM_SOUNDS_DESCRIPTION.equals(item) || ITEM_SOUND_MODE_LABEL.equals(item) || ITEM_OTHERS_TITLE.equals(item) || ITEM_OTHERS_DESCRIPTION.equals(item) || ITEM_WARP_PLATE_LABEL.equals(item) || ITEM_EXTERNAL_TELEPORT_LABEL.equals(item) || ITEM_ZOOM_STAGE_GLIDE_TICKS_LABEL.equals(item) || ITEM_ZOOM_OUT_TICKS_LABEL.equals(item) || ITEM_ZOOM_IN_TICKS_LABEL.equals(item) || ITEM_BODY_GLIDE_TICKS_LABEL.equals(item) || ITEM_PLAYER_HIDE_LABEL.equals(item);
    }

    private boolean isCenteredTextItem(String item) {
        return ITEM_TITLE.equals(item) || ITEM_GENERAL_TITLE.equals(item) || ITEM_ADVANCED1_TITLE.equals(item) || ITEM_ADVANCED2_TITLE.equals(item) || ITEM_ADVANCED3_TITLE.equals(item) || ITEM_SOUNDS_TITLE.equals(item) || ITEM_OTHERS_TITLE.equals(item);
    }

    private boolean isMutedTextItem(String item) {
        return ITEM_DESCRIPTION.equals(item) || ITEM_GENERAL_DESCRIPTION.equals(item) || ITEM_ADVANCED1_DESCRIPTION.equals(item) || ITEM_ADVANCED2_DESCRIPTION.equals(item) || ITEM_ADVANCED3_DESCRIPTION.equals(item) || ITEM_SOUNDS_DESCRIPTION.equals(item) || ITEM_OTHERS_DESCRIPTION.equals(item) || ITEM_EXTERNAL_TELEPORT_LABEL.equals(item) && !this.isExternalTeleportToggleAvailable() || ITEM_STATUS_UNLINKED.equals(item);
    }

    private boolean isManualButtonTextItem(String item) {
        return ITEM_EFFECT_TOGGLE.equals(item) || ITEM_MOVEMENT_TOGGLE.equals(item) || ITEM_CROSS_DIMENSION_TRAVEL_TOGGLE.equals(item) || ITEM_SOUND_MODE_TOGGLE.equals(item) || ITEM_WARP_PLATE_TOGGLE.equals(item) || ITEM_EXTERNAL_TELEPORT_TOGGLE.equals(item) || this.isPageTabItem(item) || ITEM_PREV_PAGE_BUTTON.equals(item) || ITEM_NEXT_PAGE_BUTTON.equals(item) || ITEM_RESET_BUTTON.equals(item) || ITEM_DONE_BUTTON.equals(item);
    }

    private void drawButtonText(GuiGraphics context, String item) {
        LayoutRect rect = this.getItemRect(item);
        Component text = this.getItemComponent(item);
        double scale = this.getButtonTextScale(rect, text);
        Objects.requireNonNull(this.f_96547_);
        int textHeight = Math.max(1, (int)Math.round(9.0 * scale));
        int y = rect.y + (rect.height - textHeight) / 2;
        GtaLikeTeleportConfigScreen.drawScaledCenteredText(context, this.f_96547_, text, rect.x + rect.width / 2, y, -1, scale);
    }

    private double getButtonTextScale(LayoutRect rect, Component text) {
        double scale = Math.max(0.25, Math.min(4.0, (double)rect.height / 20.0));
        int textWidth = this.f_96547_.m_92852_((FormattedText)text);
        int maxTextWidth = Math.max(1, rect.width - 8);
        if (textWidth > 0 && (double)textWidth * scale > (double)maxTextWidth) {
            scale = (double)maxTextWidth / (double)textWidth;
        }
        return Math.max(0.25, Math.min(4.0, scale));
    }

    private Component getItemComponent(String item) {
        return Component.m_237113_((String)this.getItemText(item));
    }

    private String getItemText(String item) {
        if (!this.itemSupportsText(item) || ITEM_DONE_BUTTON.equals(item)) {
            return this.getDefaultItemText(item);
        }
        return GtaLikeTeleportConfig.getConfigText(item, this.getDefaultItemText(item));
    }

    private String getDefaultItemText(String item) {
        if (ITEM_TAB_GENERAL.equals(item)) {
            return "General";
        }
        if (ITEM_TAB_ZOOM_STAGE.equals(item)) {
            return "Zoom Stage";
        }
        if (ITEM_TAB_ZOOM_STAGE_2.equals(item)) {
            return "Zoom Stage 2";
        }
        if (ITEM_TAB_SOUNDS.equals(item)) {
            return "Sounds";
        }
        if (ITEM_TAB_OTHERS.equals(item)) {
            return "Others";
        }
        if (ITEM_TITLE.equals(item)) {
            return Component.m_237115_((String)"gtalike_teleport.config.title").getString();
        }
        if (ITEM_DESCRIPTION.equals(item)) {
            return Component.m_237115_((String)"gtalike_teleport.config.description").getString();
        }
        if (ITEM_GENERAL_TITLE.equals(item)) {
            return "Grand Teleport General Settings";
        }
        if (ITEM_GENERAL_DESCRIPTION.equals(item)) {
            return "Adjust teleport behavior and input handling.";
        }
        if (ITEM_ADVANCED1_TITLE.equals(item)) {
            return "Grand Teleport Advanced 1";
        }
        if (ITEM_ADVANCED1_DESCRIPTION.equals(item)) {
            return "Tune extra glide used by zoom stages.";
        }
        if (ITEM_ADVANCED2_TITLE.equals(item)) {
            return "Grand Teleport Zoom Stage 2";
        }
        if (ITEM_ADVANCED2_DESCRIPTION.equals(item)) {
            return "Set tick lengths for each zoom stage.";
        }
        if (ITEM_ADVANCED3_TITLE.equals(item)) {
            return "Grand Teleport Advanced 3";
        }
        if (ITEM_ADVANCED3_DESCRIPTION.equals(item)) {
            return "Tune body camera height and player hiding.";
        }
        if (ITEM_SOUNDS_TITLE.equals(item)) {
            return "Grand Teleport Sound Settings";
        }
        if (ITEM_SOUNDS_DESCRIPTION.equals(item)) {
            return "Choose the teleport sound source and volume.";
        }
        if (ITEM_SOUND_MODE_LABEL.equals(item)) {
            return "Teleport sound source";
        }
        if (ITEM_SOUND_MODE_TOGGLE.equals(item)) {
            return this.customSoundsEnabled ? "Grand Teleport" : "Minecraft";
        }
        if (ITEM_MINECRAFT_VOLUME_SLIDER.equals(item)) {
            return "Minecraft sound volume";
        }
        if (ITEM_CUSTOM_VOLUME_SLIDER.equals(item)) {
            return "Custom sound volume";
        }
        if (ITEM_OTHERS_TITLE.equals(item)) {
            return "Grand Teleport Other Settings";
        }
        if (ITEM_OTHERS_DESCRIPTION.equals(item)) {
            return "Control third-party and server-triggered teleport effects.";
        }
        if (ITEM_WARP_PLATE_LABEL.equals(item)) {
            return "Waystones WarpPlate effect";
        }
        if (ITEM_WARP_PLATE_TOGGLE.equals(item)) {
            return this.warpPlateTransitionsEnabled ? "ON" : "OFF";
        }
        if (ITEM_EXTERNAL_TELEPORT_LABEL.equals(item)) {
            return "External teleport effect";
        }
        if (ITEM_EXTERNAL_TELEPORT_TOGGLE.equals(item)) {
            return this.isExternalTeleportToggleAvailable() ? (this.externalTeleportTransitionsEnabled ? "ON" : "OFF") : "SERVER OFF";
        }
        if (ITEM_STATUS_LINKED.equals(item)) {
            return Component.m_237115_((String)"gtalike_teleport.config.linked").getString();
        }
        if (ITEM_STATUS_UNLINKED.equals(item)) {
            return Component.m_237115_((String)"gtalike_teleport.config.unlinked").getString();
        }
        if (ITEM_EFFECT_LABEL.equals(item)) {
            return "Grand Teleport effect";
        }
        if (ITEM_MOVEMENT_LABEL.equals(item)) {
            return "Allow movement during teleport";
        }
        if (ITEM_CROSS_DIMENSION_TRAVEL_LABEL.equals(item)) {
            return "Cross-dimension camera travel";
        }
        if (ITEM_ZOOM_STAGE_GLIDE_SLIDER.equals(item)) {
            return "Zoom glide height";
        }
        if (ITEM_ZOOM_STAGE_GLIDE_TICKS_LABEL.equals(item)) {
            return "Zoom glide ticks";
        }
        if (ITEM_ZOOM_OUT_TICKS_LABEL.equals(item)) {
            return "Zoom-out stage ticks";
        }
        if (ITEM_ZOOM_IN_TICKS_LABEL.equals(item)) {
            return "Zoom-in stage ticks";
        }
        if (ITEM_BODY_HEIGHT_SLIDER.equals(item)) {
            return "Body camera height";
        }
        if (ITEM_BODY_GLIDE_SLIDER.equals(item)) {
            return "Body glide height";
        }
        if (ITEM_BODY_GLIDE_TICKS_LABEL.equals(item)) {
            return "Body glide ticks";
        }
        if (ITEM_PLAYER_HIDE_LABEL.equals(item)) {
            return "Hide player model";
        }
        if (ITEM_EFFECT_TOGGLE.equals(item)) {
            return this.effectEnabled ? "ON" : "OFF";
        }
        if (ITEM_MOVEMENT_TOGGLE.equals(item)) {
            return this.movementAllowed ? "ON" : "OFF";
        }
        if (ITEM_CROSS_DIMENSION_TRAVEL_TOGGLE.equals(item)) {
            return this.crossDimensionTravelEnabled ? "ON" : "OFF";
        }
        if (ITEM_PREV_PAGE_BUTTON.equals(item)) {
            return "<<";
        }
        if (ITEM_NEXT_PAGE_BUTTON.equals(item)) {
            return ">>";
        }
        if (ITEM_LINKED_SLIDER.equals(item)) {
            return Component.m_237115_((String)"gtalike_teleport.config.zoom_heights").getString();
        }
        if (ITEM_ZOOM_OUT_SLIDER.equals(item)) {
            return Component.m_237115_((String)"gtalike_teleport.config.zoom_out_heights").getString();
        }
        if (ITEM_ZOOM_IN_SLIDER.equals(item)) {
            return Component.m_237115_((String)"gtalike_teleport.config.zoom_in_heights").getString();
        }
        if (ITEM_RESET_BUTTON.equals(item)) {
            return Component.m_237115_((String)"gtalike_teleport.config.reset").getString();
        }
        if (ITEM_DONE_BUTTON.equals(item)) {
            return "Close";
        }
        return item;
    }

    private List<String> getVisibleLayoutItems() {
        ArrayList<String> items = new ArrayList<String>();
        items.add(ITEM_PANEL);
        for (ConfigPage page : ConfigPage.values()) {
            items.add(page.tabItem);
        }
        if (this.currentPage == ConfigPage.ZOOM) {
            items.add(ITEM_TITLE);
            items.add(ITEM_DESCRIPTION);
            items.add(ITEM_PREV_PAGE_BUTTON);
            items.add(ITEM_NEXT_PAGE_BUTTON);
            items.add(ITEM_ZOOM_OUT_SLIDER);
            items.add(ITEM_ZOOM_IN_SLIDER);
            items.add(ITEM_DIMENSION_OVERWORLD);
            items.add(ITEM_DIMENSION_NETHER);
            items.add(ITEM_DIMENSION_END);
            items.add(ITEM_LINK_BUTTON);
        } else if (this.currentPage == ConfigPage.GENERAL) {
            items.add(ITEM_GENERAL_TITLE);
            items.add(ITEM_GENERAL_DESCRIPTION);
            items.add(ITEM_PREV_PAGE_BUTTON);
            items.add(ITEM_NEXT_PAGE_BUTTON);
            items.add(ITEM_EFFECT_LABEL);
            items.add(ITEM_EFFECT_TOGGLE);
            items.add(ITEM_MOVEMENT_LABEL);
            items.add(ITEM_MOVEMENT_TOGGLE);
            items.add(ITEM_CROSS_DIMENSION_TRAVEL_LABEL);
            items.add(ITEM_CROSS_DIMENSION_TRAVEL_TOGGLE);
        } else if (this.currentPage == ConfigPage.ZOOM_STAGE_2) {
            items.add(ITEM_ADVANCED2_TITLE);
            items.add(ITEM_ADVANCED2_DESCRIPTION);
            items.add(ITEM_PREV_PAGE_BUTTON);
            items.add(ITEM_NEXT_PAGE_BUTTON);
            items.add(ITEM_ZOOM_OUT_TICKS_LABEL);
            items.add(ITEM_ZOOM_OUT_TICKS_FIELD);
            items.add(ITEM_ZOOM_IN_TICKS_LABEL);
            items.add(ITEM_ZOOM_IN_TICKS_FIELD);
        } else if (this.currentPage == ConfigPage.SOUNDS) {
            items.add(ITEM_SOUNDS_TITLE);
            items.add(ITEM_SOUNDS_DESCRIPTION);
            items.add(ITEM_PREV_PAGE_BUTTON);
            items.add(ITEM_NEXT_PAGE_BUTTON);
            items.add(ITEM_SOUND_MODE_LABEL);
            items.add(ITEM_SOUND_MODE_TOGGLE);
            items.add(ITEM_MINECRAFT_VOLUME_SLIDER);
            items.add(ITEM_CUSTOM_VOLUME_SLIDER);
        } else if (this.currentPage == ConfigPage.OTHERS) {
            items.add(ITEM_OTHERS_TITLE);
            items.add(ITEM_OTHERS_DESCRIPTION);
            items.add(ITEM_PREV_PAGE_BUTTON);
            items.add(ITEM_NEXT_PAGE_BUTTON);
            items.add(ITEM_WARP_PLATE_LABEL);
            items.add(ITEM_WARP_PLATE_TOGGLE);
            items.add(ITEM_EXTERNAL_TELEPORT_LABEL);
            items.add(ITEM_EXTERNAL_TELEPORT_TOGGLE);
        }
        items.add(ITEM_RESET_BUTTON);
        items.add(ITEM_DONE_BUTTON);
        return items;
    }

    private boolean isVisibleLayoutItem(String item) {
        return this.getVisibleLayoutItems().contains(item);
    }

    private String getStatusItemId() {
        return this.linked ? ITEM_STATUS_LINKED : ITEM_STATUS_UNLINKED;
    }

    private LayoutRect getSelectableRect(String item) {
        if (ITEM_PANEL.equals(item)) {
            return GtaLikeTeleportConfigScreen.toOuterRect(this.getContentRect());
        }
        return this.getItemRect(item);
    }

    private LayoutRect getEditableRect(String item) {
        if (ITEM_PANEL.equals(item)) {
            return this.getContentRect();
        }
        return this.getItemRect(item);
    }

    private LayoutRect getItemRect(String item) {
        LayoutRect anchored;
        if (this.editingRect != null && item != null && item.equals(this.editingLayoutItem)) {
            return ITEM_PANEL.equals(item) ? this.getContentRect() : this.constrainItemRect(item, this.editingRect);
        }
        if (ITEM_PANEL.equals(item)) {
            return this.getContentRect();
        }
        LayoutRect panel = this.getContentRect();
        if (this.shouldUseCustomWidgetLayout(item)) {
            double[] layout = GtaLikeTeleportConfig.getConfigWidgetLayout(item);
            return this.constrainCustomItemRect(item, this.getScaledItemRect(panel, layout));
        }
        if (GtaLikeTeleportConfigScreen.isDimensionButtonItem(item) && (anchored = this.getStatusAnchoredDimensionButtonRect(item, panel)) != null) {
            return this.constrainCustomItemRect(item, anchored);
        }
        return this.getDefaultItemRect(item, panel);
    }

    private LayoutRect getScaledItemRect(LayoutRect panel, double[] layout) {
        LayoutRect storedBasePanel;
        int basePanelHeight;
        int basePanelWidth = layout.length > 5 ? (int)Math.round(layout[4]) : 0;
        int n = basePanelHeight = layout.length > 5 ? (int)Math.round(layout[5]) : 0;
        if ((basePanelWidth <= 0 || basePanelHeight <= 0) && (storedBasePanel = this.getStoredBasePanelRect()) != null) {
            basePanelWidth = storedBasePanel.width;
            basePanelHeight = storedBasePanel.height;
        }
        if (basePanelWidth > 0 && basePanelHeight > 0) {
            double scale = Math.min((double)panel.width / (double)basePanelWidth, (double)panel.height / (double)basePanelHeight);
            int scaledBaseWidth = (int)Math.round((double)basePanelWidth * scale);
            int scaledBaseHeight = (int)Math.round((double)basePanelHeight * scale);
            int offsetX = panel.x + (panel.width - scaledBaseWidth) / 2;
            int offsetY = panel.y + (panel.height - scaledBaseHeight) / 2;
            return new LayoutRect(offsetX + (int)Math.round(layout[0] * (double)basePanelWidth * scale), offsetY + (int)Math.round(layout[1] * (double)basePanelHeight * scale), (int)Math.round(layout[2] * (double)basePanelWidth * scale), (int)Math.round(layout[3] * (double)basePanelHeight * scale));
        }
        return new LayoutRect(panel.x + (int)Math.round(layout[0] * (double)panel.width), panel.y + (int)Math.round(layout[1] * (double)panel.height), (int)Math.round(layout[2] * (double)panel.width), (int)Math.round(layout[3] * (double)panel.height));
    }

    private LayoutRect getStatusAnchoredDimensionButtonRect(String item, LayoutRect panel) {
        String statusItem;
        String string = statusItem = this.shouldUseCustomWidgetLayout(ITEM_STATUS_LINKED) ? ITEM_STATUS_LINKED : ITEM_STATUS_UNLINKED;
        if (!this.shouldUseCustomWidgetLayout(statusItem)) {
            return null;
        }
        LayoutRect statusRect = this.getScaledItemRect(panel, GtaLikeTeleportConfig.getConfigWidgetLayout(statusItem));
        int size = GtaLikeTeleportConfigScreen.clamp(Math.max(18, statusRect.height + 8), 16, 28);
        int gap = Math.max(2, size / 5);
        int totalWidth = size * 3 + gap * 2;
        int index = GtaLikeTeleportConfigScreen.dimensionButtonIndex(item);
        int x = statusRect.centerX() - totalWidth / 2 + index * (size + gap);
        int y = statusRect.centerY() - size / 2;
        return new LayoutRect(x, y, size, size);
    }

    private LayoutRect getDefaultDimensionButtonRect(String item, LayoutRect panel) {
        int size = 20;
        int gap = 4;
        int totalWidth = size * 3 + gap * 2;
        int x = panel.x + panel.width / 2 - totalWidth / 2 + GtaLikeTeleportConfigScreen.dimensionButtonIndex(item) * (size + gap);
        int y = this.getBottomButtonY(panel) + 22;
        if (y + size > panel.bottom()) {
            y = this.getBottomButtonY(panel) - size - 6;
        }
        return new LayoutRect(x, y, size, size);
    }

    private static int dimensionButtonIndex(String item) {
        if (ITEM_DIMENSION_NETHER.equals(item)) {
            return 1;
        }
        if (ITEM_DIMENSION_END.equals(item)) {
            return 2;
        }
        return 0;
    }

    private static boolean isDimensionButtonItem(String item) {
        return ITEM_DIMENSION_OVERWORLD.equals(item) || ITEM_DIMENSION_NETHER.equals(item) || ITEM_DIMENSION_END.equals(item);
    }

    private LayoutRect getDefaultItemRect(String item, LayoutRect panel) {
        int sliderWidth = Math.max(160, panel.width - 48);
        int sliderY = this.getFirstSliderY(panel);
        if (this.isPageTabItem(item)) {
            return this.getDefaultPageTabRect(item);
        }
        if (this.isPageTitleItem(item)) {
            int textWidth = Math.max(64, this.f_96547_.m_92852_((FormattedText)this.getItemComponent(item)) + 10);
            return new LayoutRect(panel.x + (panel.width - textWidth) / 2, panel.y, textWidth, 10);
        }
        if (this.isPageDescriptionItem(item)) {
            int textWidth = Math.max(120, this.f_96547_.m_92852_((FormattedText)this.getItemComponent(item)) + 4);
            return new LayoutRect(panel.x + Math.max(0, (panel.width - textWidth) / 2), panel.y + 24, Math.min(panel.width, textWidth), 10);
        }
        if (ITEM_PREV_PAGE_BUTTON.equals(item)) {
            double scale = this.getContentScale();
            int width = Math.max(24, (int)Math.round(34.0 * scale));
            int height = Math.max(16, (int)Math.round(20.0 * scale));
            return new LayoutRect(panel.x, panel.y - Math.max(2, height / 10), width, height);
        }
        if (ITEM_NEXT_PAGE_BUTTON.equals(item)) {
            double scale = this.getContentScale();
            int width = Math.max(24, (int)Math.round(34.0 * scale));
            int height = Math.max(16, (int)Math.round(20.0 * scale));
            return new LayoutRect(panel.x + panel.width - width, panel.y - Math.max(2, height / 10), width, height);
        }
        if (GtaLikeTeleportConfigScreen.isDimensionButtonItem(item)) {
            return this.getDefaultDimensionButtonRect(item, panel);
        }
        if (ITEM_STATUS_LINKED.equals(item) || ITEM_STATUS_UNLINKED.equals(item)) {
            return new LayoutRect(panel.x + panel.width - 156, sliderY + 20, 150, 10);
        }
        if (ITEM_LINKED_SLIDER.equals(item) || ITEM_ZOOM_OUT_SLIDER.equals(item)) {
            return new LayoutRect(panel.x, sliderY, sliderWidth, 44);
        }
        if (ITEM_ZOOM_IN_SLIDER.equals(item)) {
            return new LayoutRect(panel.x, sliderY + 52, sliderWidth, 44);
        }
        if (ITEM_EFFECT_LABEL.equals(item)) {
            return new LayoutRect(panel.x + 20, panel.y + 58, Math.max(120, panel.width - 170), 12);
        }
        if (ITEM_EFFECT_TOGGLE.equals(item)) {
            return new LayoutRect(panel.x + panel.width - 112, panel.y + 52, 92, 20);
        }
        if (ITEM_MOVEMENT_LABEL.equals(item)) {
            return new LayoutRect(panel.x + 20, panel.y + 92, Math.max(120, panel.width - 170), 12);
        }
        if (ITEM_MOVEMENT_TOGGLE.equals(item)) {
            return new LayoutRect(panel.x + panel.width - 112, panel.y + 86, 92, 20);
        }
        if (ITEM_CROSS_DIMENSION_TRAVEL_LABEL.equals(item)) {
            return new LayoutRect(panel.x + 20, panel.y + 126, Math.max(120, panel.width - 170), 12);
        }
        if (ITEM_CROSS_DIMENSION_TRAVEL_TOGGLE.equals(item)) {
            return new LayoutRect(panel.x + panel.width - 112, panel.y + 120, 92, 20);
        }
        if (ITEM_SOUND_MODE_LABEL.equals(item)) {
            return new LayoutRect(panel.x + 20, panel.y + 58, Math.max(120, panel.width - 190), 12);
        }
        if (ITEM_SOUND_MODE_TOGGLE.equals(item)) {
            return new LayoutRect(panel.x + panel.width - 132, panel.y + 52, 112, 20);
        }
        if (ITEM_WARP_PLATE_LABEL.equals(item)) {
            return new LayoutRect(panel.x + 20, panel.y + 58, Math.max(120, panel.width - 190), 12);
        }
        if (ITEM_WARP_PLATE_TOGGLE.equals(item)) {
            return new LayoutRect(panel.x + panel.width - 132, panel.y + 52, 112, 20);
        }
        if (ITEM_EXTERNAL_TELEPORT_LABEL.equals(item)) {
            return new LayoutRect(panel.x + 20, panel.y + 92, Math.max(120, panel.width - 190), 12);
        }
        if (ITEM_EXTERNAL_TELEPORT_TOGGLE.equals(item)) {
            return new LayoutRect(panel.x + panel.width - 132, panel.y + 86, 112, 20);
        }
        if (ITEM_MINECRAFT_VOLUME_SLIDER.equals(item)) {
            return new LayoutRect(panel.x, panel.y + 82, sliderWidth, 44);
        }
        if (ITEM_CUSTOM_VOLUME_SLIDER.equals(item)) {
            return new LayoutRect(panel.x, panel.y + 134, sliderWidth, 44);
        }
        if (ITEM_ZOOM_STAGE_GLIDE_SLIDER.equals(item)) {
            return new LayoutRect(panel.x, sliderY, sliderWidth, 44);
        }
        if (ITEM_ZOOM_STAGE_GLIDE_TICKS_LABEL.equals(item)) {
            return new LayoutRect(panel.x + 20, sliderY + 58, Math.max(120, panel.width - 160), 12);
        }
        if (ITEM_ZOOM_STAGE_GLIDE_TICKS_FIELD.equals(item)) {
            return new LayoutRect(panel.x + panel.width - 112, sliderY + 52, 92, 20);
        }
        if (ITEM_ZOOM_OUT_TICKS_LABEL.equals(item)) {
            return new LayoutRect(panel.x + 20, sliderY + 14, Math.max(120, panel.width - 210), 12);
        }
        if (ITEM_ZOOM_OUT_TICKS_FIELD.equals(item)) {
            return new LayoutRect(panel.x + panel.width - 170, sliderY + 8, 150, 20);
        }
        if (ITEM_ZOOM_IN_TICKS_LABEL.equals(item)) {
            return new LayoutRect(panel.x + 20, sliderY + 58, Math.max(120, panel.width - 210), 12);
        }
        if (ITEM_ZOOM_IN_TICKS_FIELD.equals(item)) {
            return new LayoutRect(panel.x + panel.width - 170, sliderY + 52, 150, 20);
        }
        if (ITEM_BODY_HEIGHT_SLIDER.equals(item)) {
            return new LayoutRect(panel.x, sliderY, sliderWidth, 44);
        }
        if (ITEM_BODY_GLIDE_SLIDER.equals(item)) {
            return new LayoutRect(panel.x, sliderY + 44, sliderWidth, 44);
        }
        if (ITEM_BODY_GLIDE_TICKS_LABEL.equals(item)) {
            return new LayoutRect(panel.x + 20, sliderY + 102, Math.max(120, panel.width - 160), 12);
        }
        if (ITEM_BODY_GLIDE_TICKS_FIELD.equals(item)) {
            return new LayoutRect(panel.x + panel.width - 112, sliderY + 96, 92, 20);
        }
        if (ITEM_PLAYER_HIDE_LABEL.equals(item)) {
            return new LayoutRect(panel.x + 20, sliderY + 132, Math.max(120, panel.width - 160), 12);
        }
        if (ITEM_PLAYER_HIDE_TICKS_FIELD.equals(item)) {
            return new LayoutRect(panel.x + panel.width - 112, sliderY + 126, 92, 20);
        }
        if (ITEM_LINK_BUTTON.equals(item)) {
            return new LayoutRect(panel.x + panel.width / 2 - 10, this.getBottomButtonY(panel), 20, 20);
        }
        if (ITEM_RESET_BUTTON.equals(item)) {
            return new LayoutRect(panel.x, this.getBottomButtonY(panel), 150, 20);
        }
        if (ITEM_DONE_BUTTON.equals(item)) {
            return new LayoutRect(panel.x + panel.width - 150, this.getBottomButtonY(panel), 150, 20);
        }
        return new LayoutRect(panel.x, panel.y, 20, 20);
    }

    private LayoutRect constrainItemRect(String item, LayoutRect rect) {
        LayoutRect panel = this.getContentRect();
        int minWidth = 8;
        int minHeight = 8;
        if (ITEM_LINK_BUTTON.equals(item) || GtaLikeTeleportConfigScreen.isDimensionButtonItem(item)) {
            minWidth = 16;
            minHeight = 16;
        } else if (ITEM_PREV_PAGE_BUTTON.equals(item) || ITEM_NEXT_PAGE_BUTTON.equals(item) || this.isPageTabItem(item)) {
            minWidth = 24;
            minHeight = 16;
        } else if (ITEM_EFFECT_TOGGLE.equals(item) || ITEM_MOVEMENT_TOGGLE.equals(item) || ITEM_CROSS_DIMENSION_TRAVEL_TOGGLE.equals(item) || ITEM_SOUND_MODE_TOGGLE.equals(item) || ITEM_WARP_PLATE_TOGGLE.equals(item) || ITEM_EXTERNAL_TELEPORT_TOGGLE.equals(item)) {
            minWidth = 44;
            minHeight = 18;
        } else if (this.isTickFieldItem(item)) {
            minWidth = 34;
            minHeight = 16;
        } else if (this.isSingleValueSliderItem(item)) {
            minWidth = 100;
            minHeight = 44;
        } else if (ITEM_RESET_BUTTON.equals(item) || ITEM_DONE_BUTTON.equals(item)) {
            minWidth = 60;
            minHeight = 18;
        } else if (ITEM_LINKED_SLIDER.equals(item) || ITEM_ZOOM_OUT_SLIDER.equals(item) || ITEM_ZOOM_IN_SLIDER.equals(item)) {
            minWidth = 120;
            minHeight = 44;
        } else if (this.itemSupportsText(item)) {
            minWidth = 20;
            minHeight = 8;
        }
        int availableWidth = Math.max(4, this.f_96543_ - 16);
        int availableHeight = Math.max(4, this.f_96544_ - 16);
        int effectiveMinWidth = Math.min(minWidth, availableWidth);
        int effectiveMinHeight = Math.min(minHeight, availableHeight);
        int maxWidth = Math.max(effectiveMinWidth, availableWidth);
        int maxHeight = Math.max(effectiveMinHeight, availableHeight);
        int itemWidth = GtaLikeTeleportConfigScreen.clamp(rect.width, effectiveMinWidth, maxWidth);
        int itemHeight = GtaLikeTeleportConfigScreen.clamp(rect.height, effectiveMinHeight, maxHeight);
        if (ITEM_LINK_BUTTON.equals(item) || GtaLikeTeleportConfigScreen.isDimensionButtonItem(item)) {
            int size;
            itemWidth = size = GtaLikeTeleportConfigScreen.clamp(Math.min(itemWidth, itemHeight), effectiveMinWidth, Math.min(maxWidth, maxHeight));
            itemHeight = size;
        }
        int x = GtaLikeTeleportConfigScreen.clamp(rect.x, 0, Math.max(0, this.f_96543_ - itemWidth));
        int y = GtaLikeTeleportConfigScreen.clamp(rect.y, 0, Math.max(0, this.f_96544_ - itemHeight));
        if (!ITEM_PANEL.equals(item) && panel.width > 0 && panel.height > 0) {
            x = GtaLikeTeleportConfigScreen.clamp(x, panel.x - panel.width, panel.x + panel.width * 2);
            y = GtaLikeTeleportConfigScreen.clamp(y, panel.y - panel.height, panel.y + panel.height * 2);
        }
        return new LayoutRect(x, y, itemWidth, itemHeight);
    }

    private LayoutRect constrainCustomItemRect(String item, LayoutRect rect) {
        int minHeight;
        int minWidth = ITEM_LINK_BUTTON.equals(item) || GtaLikeTeleportConfigScreen.isDimensionButtonItem(item) ? 4 : 1;
        int n = minHeight = ITEM_LINK_BUTTON.equals(item) || GtaLikeTeleportConfigScreen.isDimensionButtonItem(item) ? 4 : 1;
        if (ITEM_PREV_PAGE_BUTTON.equals(item) || ITEM_NEXT_PAGE_BUTTON.equals(item) || this.isPageTabItem(item) || ITEM_EFFECT_TOGGLE.equals(item) || ITEM_MOVEMENT_TOGGLE.equals(item) || ITEM_CROSS_DIMENSION_TRAVEL_TOGGLE.equals(item) || ITEM_SOUND_MODE_TOGGLE.equals(item) || ITEM_WARP_PLATE_TOGGLE.equals(item) || ITEM_EXTERNAL_TELEPORT_TOGGLE.equals(item) || this.isTickFieldItem(item)) {
            minWidth = 4;
            minHeight = 4;
        }
        int maxWidth = Math.max(minWidth, Math.max(4, this.f_96543_ - 16));
        int maxHeight = Math.max(minHeight, Math.max(4, this.f_96544_ - 16));
        int itemWidth = GtaLikeTeleportConfigScreen.clamp(rect.width, minWidth, maxWidth);
        int itemHeight = GtaLikeTeleportConfigScreen.clamp(rect.height, minHeight, maxHeight);
        if (this.isSingleValueSliderItem(item)) {
            itemHeight = Math.max(minHeight, Math.min(44, maxHeight));
        } else if (ITEM_LINKED_SLIDER.equals(item) || ITEM_ZOOM_OUT_SLIDER.equals(item) || ITEM_ZOOM_IN_SLIDER.equals(item)) {
            itemHeight = Math.max(minHeight, Math.min(44, maxHeight));
        }
        if (ITEM_LINK_BUTTON.equals(item) || GtaLikeTeleportConfigScreen.isDimensionButtonItem(item)) {
            int size;
            itemWidth = size = GtaLikeTeleportConfigScreen.clamp(Math.min(itemWidth, itemHeight), minWidth, Math.min(maxWidth, maxHeight));
            itemHeight = size;
        }
        int x = GtaLikeTeleportConfigScreen.clamp(rect.x, 0, Math.max(0, this.f_96543_ - itemWidth));
        int y = GtaLikeTeleportConfigScreen.clamp(rect.y, 0, Math.max(0, this.f_96544_ - itemHeight));
        LayoutRect panel = this.getContentRect();
        if (panel.width > 0 && panel.height > 0) {
            x = GtaLikeTeleportConfigScreen.clamp(x, panel.x - panel.width, panel.x + panel.width * 2);
            y = GtaLikeTeleportConfigScreen.clamp(y, panel.y - panel.height, panel.y + panel.height * 2);
        }
        return new LayoutRect(x, y, itemWidth, itemHeight);
    }

    private boolean isPageTitleItem(String item) {
        return ITEM_TITLE.equals(item) || ITEM_GENERAL_TITLE.equals(item) || ITEM_ADVANCED1_TITLE.equals(item) || ITEM_ADVANCED2_TITLE.equals(item) || ITEM_ADVANCED3_TITLE.equals(item) || ITEM_SOUNDS_TITLE.equals(item) || ITEM_OTHERS_TITLE.equals(item);
    }

    private boolean isPageDescriptionItem(String item) {
        return ITEM_DESCRIPTION.equals(item) || ITEM_GENERAL_DESCRIPTION.equals(item) || ITEM_ADVANCED1_DESCRIPTION.equals(item) || ITEM_ADVANCED2_DESCRIPTION.equals(item) || ITEM_ADVANCED3_DESCRIPTION.equals(item) || ITEM_SOUNDS_DESCRIPTION.equals(item) || ITEM_OTHERS_DESCRIPTION.equals(item);
    }

    private boolean isTickFieldItem(String item) {
        return ITEM_ZOOM_STAGE_GLIDE_TICKS_FIELD.equals(item) || ITEM_ZOOM_OUT_TICKS_FIELD.equals(item) || ITEM_ZOOM_IN_TICKS_FIELD.equals(item) || ITEM_BODY_GLIDE_TICKS_FIELD.equals(item) || ITEM_PLAYER_HIDE_TICKS_FIELD.equals(item);
    }

    private boolean isSingleValueSliderItem(String item) {
        return ITEM_ZOOM_STAGE_GLIDE_SLIDER.equals(item) || ITEM_BODY_HEIGHT_SLIDER.equals(item) || ITEM_BODY_GLIDE_SLIDER.equals(item) || ITEM_MINECRAFT_VOLUME_SLIDER.equals(item) || ITEM_CUSTOM_VOLUME_SLIDER.equals(item);
    }

    private Component getLayoutDebugLabel() {
        return Component.m_237115_((String)(this.layoutDebugEnabled ? "gtalike_teleport.config.layout_debug_on" : "gtalike_teleport.config.layout_debug_off"));
    }

    private Component getAspectLabel() {
        return Component.m_237115_((String)(this.layoutAspectLocked ? "gtalike_teleport.config.layout_aspect_locked" : "gtalike_teleport.config.layout_aspect_free"));
    }

    private Component getGridLabel() {
        return Component.m_237115_((String)(this.layoutGridEnabled ? "gtalike_teleport.config.layout_grid_on" : "gtalike_teleport.config.layout_grid_off"));
    }

    private Component getSnapLabel() {
        return Component.m_237115_((String)(this.layoutSnapEnabled ? "gtalike_teleport.config.layout_snap_on" : "gtalike_teleport.config.layout_snap_off"));
    }

    private boolean shouldUseCustomConfigLayout() {
        return GtaLikeTeleportConfig.hasCustomConfigLayout();
    }

    private boolean shouldUseCustomWidgetLayout(String item) {
        return GtaLikeTeleportConfig.hasConfigWidgetLayout(item);
    }

    private double getContentScale() {
        LayoutRect basePanel = this.getStoredBasePanelRect();
        if (basePanel == null || basePanel.width <= 0 || basePanel.height <= 0) {
            return 1.0;
        }
        LayoutRect panel = this.getContentRect();
        return Math.max(0.2, Math.min(4.0, Math.min((double)panel.width / (double)basePanel.width, (double)panel.height / (double)basePanel.height)));
    }

    private LayoutRect getStoredBasePanelRect() {
        if (!this.shouldUseCustomConfigLayout()) {
            return null;
        }
        int baseWidth = GtaLikeTeleportConfig.getConfigLayoutBaseWidth();
        int baseHeight = GtaLikeTeleportConfig.getConfigLayoutBaseHeight();
        if (baseWidth <= 0 || baseHeight <= 0) {
            baseWidth = this.sessionLayoutBaseWidth;
            baseHeight = this.sessionLayoutBaseHeight;
        }
        if (baseWidth <= 0 || baseHeight <= 0) {
            return null;
        }
        double[] layout = GtaLikeTeleportConfig.getConfigLayout();
        return new LayoutRect((int)Math.round(layout[0] * (double)baseWidth), (int)Math.round(layout[1] * (double)baseHeight), Math.max(1, (int)Math.round(layout[2] * (double)baseWidth)), Math.max(1, (int)Math.round(layout[3] * (double)baseHeight)));
    }

    private int getFirstSliderY(LayoutRect panel) {
        return panel.y + 42;
    }

    private int getBottomButtonY(LayoutRect panel) {
        return panel.y + panel.height - 34;
    }

    private LayoutRect getContentRect() {
        if (ITEM_PANEL.equals(this.editingLayoutItem) && this.editingRect != null) {
            return this.constrainContentRect(this.editingRect);
        }
        if (this.shouldUseCustomConfigLayout()) {
            double[] layout = GtaLikeTeleportConfig.getConfigLayout();
            int baseWidth = GtaLikeTeleportConfig.getConfigLayoutBaseWidth();
            int baseHeight = GtaLikeTeleportConfig.getConfigLayoutBaseHeight();
            if (baseWidth <= 0 || baseHeight <= 0) {
                baseWidth = this.sessionLayoutBaseWidth;
                baseHeight = this.sessionLayoutBaseHeight;
            }
            if (baseWidth > 0 && baseHeight > 0) {
                double scale = Math.min((double)this.f_96543_ / (double)baseWidth, (double)this.f_96544_ / (double)baseHeight);
                int scaledBaseWidth = (int)Math.round((double)baseWidth * scale);
                int scaledBaseHeight = (int)Math.round((double)baseHeight * scale);
                int offsetX = (this.f_96543_ - scaledBaseWidth) / 2;
                int offsetY = (this.f_96544_ - scaledBaseHeight) / 2;
                return this.constrainScaledContentRect(new LayoutRect(offsetX + (int)Math.round(layout[0] * (double)baseWidth * scale), offsetY + (int)Math.round(layout[1] * (double)baseHeight * scale), (int)Math.round(layout[2] * (double)baseWidth * scale), (int)Math.round(layout[3] * (double)baseHeight * scale)));
            }
            return this.constrainContentRect(new LayoutRect((int)Math.round(layout[0] * (double)this.f_96543_), (int)Math.round(layout[1] * (double)this.f_96544_), (int)Math.round(layout[2] * (double)this.f_96543_), (int)Math.round(layout[3] * (double)this.f_96544_)));
        }
        return this.getDefaultContentRect();
    }

    private LayoutRect getDefaultContentRect() {
        int availableWidth = this.getAvailableContentWidth();
        int minWidth = Math.min(300, availableWidth);
        int targetWidth = (int)Math.round((double)this.f_96543_ * 0.58);
        int panelWidth = GtaLikeTeleportConfigScreen.clamp(targetWidth, minWidth, Math.min(680, availableWidth));
        int defaultPanelHeight = 260;
        double heightRatio = 0.31;
        int targetHeight = (int)Math.round((double)this.f_96544_ * heightRatio);
        int panelHeight = Math.max(defaultPanelHeight, targetHeight);
        int x = (this.f_96543_ - panelWidth) / 2;
        int y = Math.max(this.getContentTopMargin(), (this.f_96544_ - panelHeight) / 2);
        return this.constrainContentRect(new LayoutRect(x, y, panelWidth, panelHeight));
    }

    private LayoutRect constrainContentRect(LayoutRect rect) {
        int preferredMinHeight = 170;
        int topMargin = this.getContentTopMargin();
        int availableWidth = this.getAvailableContentWidth();
        int availableHeight = Math.max(80, this.f_96544_ - topMargin - 28);
        int minWidth = Math.min(300, availableWidth);
        int minHeight = Math.min(preferredMinHeight, availableHeight);
        int panelWidth = GtaLikeTeleportConfigScreen.clamp(rect.width, minWidth, availableWidth);
        int panelHeight = GtaLikeTeleportConfigScreen.clamp(rect.height, minHeight, availableHeight);
        int x = GtaLikeTeleportConfigScreen.clamp(rect.x, 28, Math.max(28, this.f_96543_ - 28 - panelWidth));
        int y = GtaLikeTeleportConfigScreen.clamp(rect.y, topMargin, Math.max(topMargin, this.f_96544_ - 28 - panelHeight));
        return new LayoutRect(x, y, panelWidth, panelHeight);
    }

    private LayoutRect constrainScaledContentRect(LayoutRect rect) {
        int panelHeight;
        int topMargin = this.getContentTopMargin();
        int availableWidth = this.getAvailableContentWidth();
        int availableHeight = Math.max(80, this.f_96544_ - topMargin - 28);
        int panelWidth = Math.max(1, rect.width);
        double scale = Math.min(1.0, Math.min((double)availableWidth / (double)panelWidth, (double)availableHeight / (double)(panelHeight = Math.max(1, rect.height))));
        if (scale < 1.0) {
            panelWidth = Math.max(1, (int)Math.round((double)panelWidth * scale));
            panelHeight = Math.max(1, (int)Math.round((double)panelHeight * scale));
        }
        int x = GtaLikeTeleportConfigScreen.clamp(rect.x, 28, Math.max(28, this.f_96543_ - 28 - panelWidth));
        int y = GtaLikeTeleportConfigScreen.clamp(rect.y, topMargin, Math.max(topMargin, this.f_96544_ - 28 - panelHeight));
        return new LayoutRect(x, y, panelWidth, panelHeight);
    }

    private int getAvailableContentWidth() {
        int screenLimited = Math.max(120, this.f_96543_ - 56);
        int ratioLimited = Math.max(120, (int)Math.round((double)this.f_96543_ * 0.72));
        return Math.min(screenLimited, ratioLimited);
    }

    private int getContentTopMargin() {
        return this.layoutDebugEnabled ? Math.min(84, Math.max(28, this.f_96544_ / 4)) : 28;
    }

    private static LayoutRect toOuterRect(LayoutRect panel) {
        return new LayoutRect(panel.x - 14, panel.y - 14, panel.width + 14 + 14, panel.height + 14 + 0);
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    private static enum ConfigPage {
        GENERAL("tab_general"),
        ZOOM("tab_zoom_stage"),
        ZOOM_STAGE_2("tab_zoom_stage_2"),
        SOUNDS("tab_sounds"),
        OTHERS("tab_others");

        private final String tabItem;

        private ConfigPage(String tabItem) {
            this.tabItem = tabItem;
        }
    }

    private static enum LayoutEditAction {
        NONE,
        MOVE,
        RESIZE_RIGHT,
        RESIZE_BOTTOM,
        RESIZE_CORNER;

    }

    private record LayoutRect(int x, int y, int width, int height) {
        int right() {
            return this.x + this.width;
        }

        int bottom() {
            return this.y + this.height;
        }

        int centerX() {
            return this.x + this.width / 2;
        }

        int centerY() {
            return this.y + this.height / 2;
        }

        boolean contains(double px, double py) {
            return px >= (double)this.x && px <= (double)this.right() && py >= (double)this.y && py <= (double)this.bottom();
        }
    }

    private static final class StageHeightSlider
    extends AbstractWidget {
        private static final int HEIGHT = 44;
        private static final int FRAME_INSET_Y = 2;
        private static final int BASE_WIDTH = 368;
        private static final int HANDLE_WIDTH = 7;
        private static final int TRACK_MARGIN = 14;
        private static final int TRACK_Y = 29;
        private static final int TRACK_HEIGHT = 3;
        private final Consumer<double[]> onChanged;
        private double[] values;
        private int activeHandle = -1;

        StageHeightSlider(int x, int y, int width, Component label, double[] values, Consumer<double[]> onChanged) {
            super(x, y, width, 44, label);
            this.values = GtaLikeTeleportConfig.sanitizeStageHeights(values);
            this.onChanged = onChanged;
        }

        void setValues(double[] values) {
            this.values = GtaLikeTeleportConfig.sanitizeStageHeights(values);
            this.activeHandle = -1;
        }

        void setEditable(boolean editable) {
            this.f_93623_ = editable;
            if (!editable) {
                this.activeHandle = -1;
            }
        }

        protected void m_87963_(GuiGraphics context, int mouseX, int mouseY, float tickProgress) {
            int x = this.m_252754_();
            int y = this.m_252907_();
            int right = x + this.m_5711_();
            int bottom = y + this.m_93694_();
            double scale = this.getVisualScale();
            int borderColor = !this.f_93623_ ? -11184811 : (this.m_198029_() ? -1 : -8947849);
            int textColor = this.f_93623_ ? -1 : -7829368;
            int valueColor = this.f_93623_ ? -2039584 : -8947849;
            int trackColor = this.f_93623_ ? -8947849 : -11711155;
            int frameY = this.getFrameY();
            int frameHeight = this.getFrameHeight();
            context.m_280509_(x, frameY, right, frameY + frameHeight, this.f_93623_ ? -1441458923 : -2012213232);
            context.m_280637_(x, frameY, this.m_5711_(), frameHeight, borderColor);
            Component formatted = this.formatValues();
            double textScale = this.fitTopTextScale(scale, this.m_6035_(), formatted);
            GtaLikeTeleportConfigScreen.drawScaledText(context, Minecraft.m_91087_().f_91062_, this.m_6035_(), x + StageHeightSlider.scaled(8, textScale), y + StageHeightSlider.scaled(7, textScale), textColor, textScale);
            int valuesWidth = (int)Math.round((double)Minecraft.m_91087_().f_91062_.m_92852_((FormattedText)formatted) * textScale);
            GtaLikeTeleportConfigScreen.drawScaledText(context, Minecraft.m_91087_().f_91062_, formatted, right - valuesWidth - StageHeightSlider.scaled(8, textScale), y + StageHeightSlider.scaled(7, textScale), valueColor, textScale);
            int trackLeft = x + this.getTrackMargin();
            int trackRight = right - this.getTrackMargin();
            int trackY = y + this.getTrackY();
            int trackHeight = Math.max(1, StageHeightSlider.scaled(3, scale));
            context.m_280509_(trackLeft, trackY, trackRight, trackY + trackHeight, trackColor);
            int handleWidth = Math.max(3, StageHeightSlider.scaled(7, scale));
            int handleTop = StageHeightSlider.scaled(8, scale);
            int handleBottom = StageHeightSlider.scaled(12, scale);
            for (int i = 0; i < this.values.length; ++i) {
                int handleX = this.valueToX(this.values[i]);
                int handleColor = !this.f_93623_ ? -8947849 : (i == this.activeHandle ? -1 : -3355444);
                int handleLeft = handleX - handleWidth / 2;
                int handleRight = handleLeft + handleWidth;
                context.m_280509_(handleLeft, trackY - handleTop, handleRight, trackY + handleBottom, handleColor);
            }
        }

        public boolean m_6375_(double mouseX, double mouseY, int button) {
            if (!(this.f_93623_ && this.f_93624_ && this.m_5953_(mouseX, mouseY))) {
                return false;
            }
            this.activeHandle = this.nearestHandle(mouseX);
            this.updateValueFromMouse(mouseX);
            this.m_7435_(Minecraft.m_91087_().m_91106_());
            return true;
        }

        public boolean m_7979_(double mouseX, double mouseY, int button, double dragX, double dragY) {
            if (this.activeHandle < 0) {
                return false;
            }
            this.updateValueFromMouse(mouseX);
            return true;
        }

        public boolean m_6348_(double mouseX, double mouseY, int button) {
            if (this.activeHandle < 0) {
                return false;
            }
            this.activeHandle = -1;
            return true;
        }

        protected void m_168797_(NarrationElementOutput output) {
            output.m_169146_(NarratedElementType.TITLE, (Component)Component.m_237113_((String)(this.m_6035_().getString() + ": " + this.formatValues().getString())));
        }

        private void updateValueFromMouse(double mouseX) {
            if (this.activeHandle < 0) {
                return;
            }
            double value = this.xToValue(mouseX);
            double min = this.activeHandle == 0 ? GtaLikeTeleportConfig.getMinStageHeight() : this.values[this.activeHandle - 1] + GtaLikeTeleportConfig.getMinStageGap();
            double max = this.activeHandle == this.values.length - 1 ? GtaLikeTeleportConfig.getMaxStageHeight() : this.values[this.activeHandle + 1] - GtaLikeTeleportConfig.getMinStageGap();
            this.values[this.activeHandle] = StageHeightSlider.clamp(Math.rint(value), min, max);
            this.onChanged.accept((double[])this.values.clone());
        }

        private int nearestHandle(double mouseX) {
            int nearest = 0;
            double nearestDistance = Double.MAX_VALUE;
            for (int i = 0; i < this.values.length; ++i) {
                double distance = Math.abs(mouseX - (double)this.valueToX(this.values[i]));
                if (!(distance < nearestDistance)) continue;
                nearestDistance = distance;
                nearest = i;
            }
            return nearest;
        }

        private int valueToX(double value) {
            double min = GtaLikeTeleportConfig.getMinStageHeight();
            double max = GtaLikeTeleportConfig.getMaxStageHeight();
            double progress = (value - min) / Math.max(1.0, max - min);
            int trackLeft = this.m_252754_() + this.getTrackMargin();
            int trackWidth = this.m_5711_() - this.getTrackMargin() * 2;
            return trackLeft + (int)Math.round(StageHeightSlider.clamp(progress, 0.0, 1.0) * (double)trackWidth);
        }

        private double xToValue(double mouseX) {
            int trackLeft = this.m_252754_() + this.getTrackMargin();
            int trackWidth = this.m_5711_() - this.getTrackMargin() * 2;
            double progress = (mouseX - (double)trackLeft) / Math.max(1.0, (double)trackWidth);
            return GtaLikeTeleportConfig.getMinStageHeight() + StageHeightSlider.clamp(progress, 0.0, 1.0) * (GtaLikeTeleportConfig.getMaxStageHeight() - GtaLikeTeleportConfig.getMinStageHeight());
        }

        private double getVisualScale() {
            double heightScale = (double)this.m_93694_() / 44.0;
            double widthScale = (double)this.m_5711_() / 368.0;
            return Math.max(0.25, Math.min(1.0, Math.min(heightScale, widthScale)));
        }

        private int getFrameY() {
            return this.m_252907_() + Math.min(2, Math.max(0, (this.m_93694_() - 1) / 2));
        }

        private int getFrameHeight() {
            int inset = Math.min(2, Math.max(0, (this.m_93694_() - 1) / 2));
            return Math.max(1, this.m_93694_() - inset * 2);
        }

        private double fitTopTextScale(double scale, Component label, Component value) {
            int labelWidth = Minecraft.m_91087_().f_91062_.m_92852_((FormattedText)label);
            int valueWidth = Minecraft.m_91087_().f_91062_.m_92852_((FormattedText)value);
            int rawWidth = labelWidth + valueWidth + 28;
            int availableWidth = Math.max(1, this.m_5711_() - 16);
            if (rawWidth > 0 && (double)rawWidth * scale > (double)availableWidth) {
                scale = (double)availableWidth / (double)rawWidth;
            }
            return Math.max(0.25, Math.min(1.0, scale));
        }

        private int getTrackMargin() {
            return StageHeightSlider.scaled(14, this.getVisualScale());
        }

        private int getTrackY() {
            int trackY = StageHeightSlider.scaled(29, this.getVisualScale());
            int min = Math.max(10, this.m_93694_() / 2);
            int max = Math.max(min, this.m_93694_() - 8);
            return Math.max(min, Math.min(max, trackY));
        }

        private static int scaled(int value, double scale) {
            return Math.max(1, (int)Math.round((double)value * scale));
        }

        private Component formatValues() {
            return Component.m_237113_((String)((int)this.values[0] + " / " + (int)this.values[1] + " / " + (int)this.values[2]));
        }

        private static double clamp(double value, double min, double max) {
            return Math.max(min, Math.min(max, value));
        }

        public String toString() {
            return "StageHeightSlider{" + Arrays.toString(this.values) + "}";
        }
    }

    private static final class SingleValueSlider
    extends AbstractWidget {
        private static final int HEIGHT = 44;
        private static final int FRAME_INSET_Y = 2;
        private static final int HANDLE_WIDTH = 7;
        private static final int TRACK_MARGIN = 14;
        private static final int TRACK_Y = 29;
        private static final int TRACK_HEIGHT = 3;
        private final Consumer<Double> onChanged;
        private final double min;
        private final double max;
        private final double step;
        private final boolean integerValue;
        private final String suffix;
        private double value;
        private boolean dragging;

        SingleValueSlider(int x, int y, int width, Component label, double value, double min, double max, double step, boolean integerValue, String suffix, Consumer<Double> onChanged) {
            super(x, y, width, 44, label);
            this.min = min;
            this.max = max;
            this.step = step;
            this.integerValue = integerValue;
            this.suffix = suffix;
            this.onChanged = onChanged;
            this.value = this.sanitize(value);
        }

        void setValue(double value) {
            this.value = this.sanitize(value);
        }

        protected void m_87963_(GuiGraphics context, int mouseX, int mouseY, float tickProgress) {
            int x = this.m_252754_();
            int y = this.m_252907_();
            int right = x + this.m_5711_();
            int bottom = y + this.m_93694_();
            double scale = this.getVisualScale();
            int borderColor = !this.f_93623_ ? -11184811 : (this.m_198029_() ? -1 : -8947849);
            int textColor = this.f_93623_ ? -1 : -7829368;
            int valueColor = this.f_93623_ ? -2039584 : -8947849;
            int trackColor = this.f_93623_ ? -8947849 : -11711155;
            int frameY = this.getFrameY();
            int frameHeight = this.getFrameHeight();
            context.m_280509_(x, frameY, right, frameY + frameHeight, this.f_93623_ ? -1441458923 : -2012213232);
            context.m_280637_(x, frameY, this.m_5711_(), frameHeight, borderColor);
            MutableComponent formatted = Component.m_237113_((String)this.formatValue());
            double textScale = this.fitTopTextScale(scale, this.m_6035_(), (Component)formatted);
            GtaLikeTeleportConfigScreen.drawScaledText(context, Minecraft.m_91087_().f_91062_, this.m_6035_(), x + SingleValueSlider.scaled(8, textScale), y + SingleValueSlider.scaled(7, textScale), textColor, textScale);
            int valueWidth = (int)Math.round((double)Minecraft.m_91087_().f_91062_.m_92852_((FormattedText)formatted) * textScale);
            GtaLikeTeleportConfigScreen.drawScaledText(context, Minecraft.m_91087_().f_91062_, (Component)formatted, right - valueWidth - SingleValueSlider.scaled(8, textScale), y + SingleValueSlider.scaled(7, textScale), valueColor, textScale);
            int trackLeft = x + this.getTrackMargin();
            int trackRight = right - this.getTrackMargin();
            int trackY = y + this.getTrackY();
            int trackHeight = Math.max(1, SingleValueSlider.scaled(3, scale));
            context.m_280509_(trackLeft, trackY, trackRight, trackY + trackHeight, trackColor);
            int handleWidth = Math.max(3, SingleValueSlider.scaled(7, scale));
            int handleX = this.valueToX(this.value);
            int handleLeft = handleX - handleWidth / 2;
            int handleRight = handleLeft + handleWidth;
            int handleColor = !this.f_93623_ ? -8947849 : (this.dragging ? -1 : -3355444);
            context.m_280509_(handleLeft, trackY - SingleValueSlider.scaled(8, scale), handleRight, trackY + SingleValueSlider.scaled(12, scale), handleColor);
        }

        public boolean m_6375_(double mouseX, double mouseY, int button) {
            if (!(this.f_93623_ && this.f_93624_ && this.m_5953_(mouseX, mouseY))) {
                return false;
            }
            this.dragging = true;
            this.updateValueFromMouse(mouseX);
            this.m_7435_(Minecraft.m_91087_().m_91106_());
            return true;
        }

        public boolean m_7979_(double mouseX, double mouseY, int button, double dragX, double dragY) {
            if (!this.dragging) {
                return false;
            }
            this.updateValueFromMouse(mouseX);
            return true;
        }

        public boolean m_6348_(double mouseX, double mouseY, int button) {
            if (!this.dragging) {
                return false;
            }
            this.dragging = false;
            return true;
        }

        protected void m_168797_(NarrationElementOutput output) {
            output.m_169146_(NarratedElementType.TITLE, (Component)Component.m_237113_((String)(this.m_6035_().getString() + ": " + this.formatValue())));
        }

        private void updateValueFromMouse(double mouseX) {
            double nextValue = this.sanitize(this.xToValue(mouseX));
            if (Math.abs(nextValue - this.value) < 1.0E-4) {
                return;
            }
            this.value = nextValue;
            this.onChanged.accept(this.value);
        }

        private int valueToX(double value) {
            double progress = (value - this.min) / Math.max(1.0E-4, this.max - this.min);
            int trackLeft = this.m_252754_() + this.getTrackMargin();
            int trackWidth = this.m_5711_() - this.getTrackMargin() * 2;
            return trackLeft + (int)Math.round(SingleValueSlider.clamp(progress, 0.0, 1.0) * (double)trackWidth);
        }

        private double xToValue(double mouseX) {
            int trackLeft = this.m_252754_() + this.getTrackMargin();
            int trackWidth = this.m_5711_() - this.getTrackMargin() * 2;
            double progress = (mouseX - (double)trackLeft) / Math.max(1.0, (double)trackWidth);
            return this.min + SingleValueSlider.clamp(progress, 0.0, 1.0) * (this.max - this.min);
        }

        private double sanitize(double rawValue) {
            double clamped = SingleValueSlider.clamp(rawValue, this.min, this.max);
            if (this.step > 0.0) {
                clamped = (double)Math.round(clamped / this.step) * this.step;
            }
            if (this.integerValue) {
                clamped = Math.round(clamped);
            }
            return SingleValueSlider.clamp(clamped, this.min, this.max);
        }

        private String formatValue() {
            if (this.integerValue) {
                return Integer.toString((int)Math.round(this.value)) + this.suffix;
            }
            return String.format(Locale.ROOT, "%.1f%s", this.value, this.suffix);
        }

        private double getVisualScale() {
            return Math.max(0.45, Math.min(4.0, (double)this.m_93694_() / 44.0));
        }

        private int getFrameY() {
            return this.m_252907_() + Math.min(2, Math.max(0, (this.m_93694_() - 1) / 2));
        }

        private int getFrameHeight() {
            int inset = Math.min(2, Math.max(0, (this.m_93694_() - 1) / 2));
            return Math.max(1, this.m_93694_() - inset * 2);
        }

        private double fitTopTextScale(double scale, Component label, Component value) {
            int labelWidth = Minecraft.m_91087_().f_91062_.m_92852_((FormattedText)label);
            int valueWidth = Minecraft.m_91087_().f_91062_.m_92852_((FormattedText)value);
            int rawWidth = labelWidth + valueWidth + 28;
            int availableWidth = Math.max(1, this.m_5711_() - 16);
            if (rawWidth > 0 && (double)rawWidth * scale > (double)availableWidth) {
                scale = (double)availableWidth / (double)rawWidth;
            }
            return Math.max(0.25, Math.min(1.0, scale));
        }

        private int getTrackMargin() {
            return SingleValueSlider.scaled(14, this.getVisualScale());
        }

        private int getTrackY() {
            int trackY = SingleValueSlider.scaled(29, this.getVisualScale());
            int minY = Math.max(10, this.m_93694_() / 2);
            int maxY = Math.max(minY, this.m_93694_() - 8);
            return Math.max(minY, Math.min(maxY, trackY));
        }

        private static int scaled(int value, double scale) {
            return Math.max(1, (int)Math.round((double)value * scale));
        }

        private static double clamp(double value, double min, double max) {
            return Math.max(min, Math.min(max, value));
        }
    }

    private static final class LinkLockButton
    extends AbstractWidget {
        private final Runnable onPress;
        private boolean locked;

        LinkLockButton(int x, int y, int width, int height, Runnable onPress) {
            super(x, y, width, height, (Component)Component.m_237119_());
            this.onPress = onPress;
        }

        void setLocked(boolean locked) {
            this.locked = locked;
        }

        protected void m_87963_(GuiGraphics context, int mouseX, int mouseY, float tickProgress) {
            int x = this.m_252754_();
            int y = this.m_252907_();
            int width = this.m_5711_();
            int height = this.m_93694_();
            int background = !this.f_93623_ ? -1439682512 : (this.m_198029_() ? -866822827 : -1439024582);
            int border = !this.f_93623_ ? -8947849 : -1;
            int textColor = !this.f_93623_ ? -8947849 : -1;
            context.m_280509_(x, y, x + width, y + height, background);
            context.m_280637_(x, y, width, height, border);
            MutableComponent icon = Component.m_237113_((String)(this.locked ? "\ud83d\udd12" : "\ud83d\udd13"));
            Font font = Minecraft.m_91087_().f_91062_;
            int iconWidth = Math.max(1, font.m_92852_((FormattedText)icon));
            Objects.requireNonNull(font);
            int iconHeight = Math.max(1, 9);
            double scale = Math.min((double)(width - 4) / (double)iconWidth, (double)(height - 4) / (double)iconHeight);
            scale = Math.max(0.25, Math.min(4.0, scale));
            int textHeight = Math.max(1, (int)Math.round((double)iconHeight * scale));
            int textY = y + (height - textHeight) / 2;
            GtaLikeTeleportConfigScreen.drawScaledCenteredText(context, font, (Component)icon, x + width / 2, textY, textColor, scale);
        }

        public boolean m_6375_(double mouseX, double mouseY, int button) {
            if (!(this.f_93623_ && this.f_93624_ && this.m_5953_(mouseX, mouseY))) {
                return false;
            }
            this.onPress.run();
            this.m_7435_(Minecraft.m_91087_().m_91106_());
            return true;
        }

        protected void m_168797_(NarrationElementOutput output) {
            output.m_169146_(NarratedElementType.TITLE, this.m_6035_());
        }
    }

    private static final class DimensionIconButton
    extends AbstractWidget {
        private final ItemStack icon;
        private final ResourceLocation texture;
        private final Runnable onPress;
        private boolean selected;

        DimensionIconButton(int x, int y, int width, int height, ItemStack icon, Component label, ResourceLocation texture, Runnable onPress) {
            super(x, y, width, height, label);
            this.icon = icon.m_41777_();
            this.texture = texture;
            this.onPress = onPress;
        }

        void setSelected(boolean selected) {
            this.selected = selected;
        }

        protected void m_87963_(GuiGraphics context, int mouseX, int mouseY, float tickProgress) {
            int x = this.m_252754_();
            int y = this.m_252907_();
            int right = x + this.m_5711_();
            int bottom = y + this.m_93694_();
            int borderColor = this.selected ? -1 : (this.m_198029_() ? -5592406 : -11184811);
            context.m_280509_(x, y, right, bottom, this.selected ? -1440735200 : -1441787888);
            context.m_280637_(x, y, this.m_5711_(), this.m_93694_(), borderColor);
            double scale = Math.max(0.5, Math.min(4.0, Math.min((double)(this.m_5711_() - 4) / 16.0, (double)(this.m_93694_() - 4) / 16.0)));
            int iconX = x + (int)Math.round(((double)this.m_5711_() - 16.0 * scale) / 2.0);
            int iconY = y + (int)Math.round(((double)this.m_93694_() - 16.0 * scale) / 2.0);
            if (!this.icon.m_41619_()) {
                context.m_280168_().m_85836_();
                context.m_280168_().m_252880_((float)iconX, (float)iconY, 0.0f);
                context.m_280168_().m_85841_((float)scale, (float)scale, 1.0f);
                context.m_280480_(this.icon, 0, 0);
                context.m_280168_().m_85849_();
            } else {
                DimensionIconButton.drawTextureIcon(context, iconX, iconY, scale, this.texture);
            }
            if (!this.selected) {
                context.m_280509_(x + 1, y + 1, Math.max(x + 1, right - 1), Math.max(y + 1, bottom - 1), -1728053248);
            }
        }

        private static void drawTextureIcon(GuiGraphics context, int x, int y, double scale, ResourceLocation texture) {
            int size = Math.max(4, (int)Math.round(16.0 * scale));
            context.m_280163_(texture, x, y, 0.0f, 0.0f, size, size, 16, 16);
        }

        public boolean m_6375_(double mouseX, double mouseY, int button) {
            if (!(this.f_93623_ && this.f_93624_ && this.m_5953_(mouseX, mouseY))) {
                return false;
            }
            this.onPress.run();
            this.m_7435_(Minecraft.m_91087_().m_91106_());
            return true;
        }

        protected void m_168797_(NarrationElementOutput output) {
            output.m_169146_(NarratedElementType.TITLE, this.m_6035_());
        }
    }

    private static final class ScaledEditBox
    extends EditBox {
        private final Font font;
        private double textScale;
        private boolean selectingText;
        private int selectionAnchor;

        ScaledEditBox(Font font, int x, int y, int width, int height, Component message, double textScale) {
            super(font, x, y, width, height, message);
            this.font = font;
            this.setTextScale(textScale);
        }

        void setTextScale(double textScale) {
            this.textScale = Math.max(0.25, Math.min(4.0, textScale));
        }

        public void m_87963_(GuiGraphics context, int mouseX, int mouseY, float tickProgress) {
            int x = this.m_252754_();
            int y = this.m_252907_();
            int width = this.m_5711_();
            int height = this.m_93694_();
            int right = x + width;
            int bottom = y + height;
            int borderColor = this.m_93696_() ? -1 : -5592406;
            int textColor = this.f_93623_ ? -1 : -6250336;
            context.m_280509_(x, y, right, bottom, -16777216);
            context.m_280637_(x, y, width, height, borderColor);
            String rawValue = this.m_94155_();
            MutableComponent value = Component.m_237113_((String)rawValue);
            double scale = this.getTextScale((Component)value);
            int textHeight = this.getTextHeight(scale);
            int textX = this.getTextX(scale);
            int textY = this.getTextY(textHeight);
            this.drawSelection(context, rawValue, textX, textY, textHeight, scale);
            GtaLikeTeleportConfigScreen.drawScaledText(context, this.font, (Component)value, textX, textY, textColor, scale);
            if (this.m_93696_()) {
                int cursorPosition = Math.max(0, Math.min(this.m_94207_(), rawValue.length()));
                int cursorX = this.getCursorX(rawValue, cursorPosition, textX, scale);
                int cursorWidth = Math.max(1, (int)Math.round(scale));
                if (cursorX < right - 3) {
                    context.m_280509_(cursorX, textY - 1, cursorX + cursorWidth, textY + textHeight + 1, textColor);
                }
            }
        }

        public boolean m_6375_(double mouseX, double mouseY, int button) {
            if (!(this.f_93623_ && this.f_93624_ && button == 0 && this.m_5953_(mouseX, mouseY))) {
                return false;
            }
            int cursor = this.getCursorPositionForMouse(mouseX);
            this.m_93692_(true);
            this.m_94196_(cursor);
            this.m_94208_(cursor);
            this.selectionAnchor = cursor;
            this.selectingText = true;
            return true;
        }

        public boolean m_7979_(double mouseX, double mouseY, int button, double dragX, double dragY) {
            if (!this.selectingText || button != 0) {
                return false;
            }
            int cursor = this.getCursorPositionForMouse(mouseX);
            this.m_94196_(cursor);
            this.m_94208_(this.selectionAnchor);
            return true;
        }

        public boolean m_6348_(double mouseX, double mouseY, int button) {
            if (!this.selectingText || button != 0) {
                return false;
            }
            this.selectingText = false;
            return true;
        }

        private double getTextScale(Component text) {
            double scale = Math.max(0.45, Math.min(4.0, this.textScale));
            int textWidth = this.font.m_92852_((FormattedText)text);
            int maxTextWidth = Math.max(1, this.m_5711_() - 8);
            if (textWidth > 0 && (double)textWidth * scale > (double)maxTextWidth) {
                scale = (double)maxTextWidth / (double)textWidth;
            }
            return Math.max(0.45, Math.min(4.0, scale));
        }

        private int getTextHeight(double scale) {
            Objects.requireNonNull(this.font);
            return Math.max(1, (int)Math.round(9.0 * scale));
        }

        private int getTextX(double scale) {
            return this.m_252754_() + Math.max(4, (int)Math.round(4.0 * scale));
        }

        private int getTextY(int textHeight) {
            return this.m_252907_() + (this.m_93694_() - textHeight) / 2;
        }

        private int getCursorX(String value, int cursorPosition, int textX, double scale) {
            int cursor = Math.max(0, Math.min(cursorPosition, value.length()));
            return textX + (int)Math.round((double)this.font.m_92895_(value.substring(0, cursor)) * scale);
        }

        private int getCursorPositionForMouse(double mouseX) {
            String value = this.m_94155_();
            double scale = this.getTextScale((Component)Component.m_237113_((String)value));
            double localX = (mouseX - (double)this.getTextX(scale)) / scale;
            if (localX <= 0.0) {
                return 0;
            }
            for (int i = 1; i <= value.length(); ++i) {
                int current;
                int previous = this.font.m_92895_(value.substring(0, i - 1));
                if (!(localX < (double)(previous + (current = this.font.m_92895_(value.substring(0, i)))) / 2.0)) continue;
                return i - 1;
            }
            return value.length();
        }

        private void drawSelection(GuiGraphics context, String value, int textX, int textY, int textHeight, double scale) {
            int end;
            int selectedLength;
            String selected = this.m_94173_();
            if (selected == null || selected.isEmpty()) {
                return;
            }
            int cursor = Math.max(0, Math.min(this.m_94207_(), value.length()));
            int start = cursor - (selectedLength = selected.length());
            if (!this.matchesSelection(value, selected, start, end = cursor)) {
                start = cursor;
                end = cursor + selectedLength;
            }
            if (!this.matchesSelection(value, selected, start, end)) {
                return;
            }
            int left = this.getCursorX(value, start, textX, scale);
            int right = this.getCursorX(value, end, textX, scale);
            context.m_280509_(Math.min(left, right), textY - 1, Math.max(left, right), textY + textHeight + 1, -1439736902);
        }

        private boolean matchesSelection(String value, String selected, int start, int end) {
            return start >= 0 && end <= value.length() && start <= end && value.substring(start, end).equals(selected);
        }
    }

    private record ToolbarCursor(int x, int y) {
    }
}

