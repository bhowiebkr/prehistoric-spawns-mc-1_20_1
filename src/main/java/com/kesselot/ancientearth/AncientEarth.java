package com.kesselot.ancientearth;

import com.github.teamfossilsarcheology.fossil.entity.ModEntities;
import com.mojang.logging.LogUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.slf4j.Logger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Mod(AncientEarth.MODID)
public class AncientEarth {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "ancientearth";

    private static final SpawnPlacements.Type ON_GROUND = SpawnPlacements.Type.ON_GROUND;
    private static final SpawnPlacements.Type IN_WATER = SpawnPlacements.Type.IN_WATER;
    private static final Heightmap.Types MOTION_BLOCKING_NO_LEAVES = Heightmap.Types.MOTION_BLOCKING_NO_LEAVES;
    
    public AncientEarth() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::setup);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC, MODID + "-common.toml");
    }
    
    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntities.ALLOSAURUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.ANKYLOSAURUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.AQUILOLAMNA.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricWaterSpawnRules);
            SpawnPlacements.register(ModEntities.ARTHROPLEURA.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.BRACHIOSAURUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.CERATOSAURUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.CITIPATI.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.COMPSOGNATHUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRulesAllowDarkness);
            SpawnPlacements.register(ModEntities.CONFUCIUSORNIS.get(), ON_GROUND, Heightmap.Types.MOTION_BLOCKING, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.CRASSIGYRINUS.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricWaterSpawnRules);
            SpawnPlacements.register(ModEntities.DEINONYCHUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRulesAllowDarkness);
            SpawnPlacements.register(ModEntities.DICRANURUS.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricWaterSpawnRules);
            SpawnPlacements.register(ModEntities.DILOPHOSAURUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.DIMETRODON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.DIMORPHODON.get(), ON_GROUND, Heightmap.Types.MOTION_BLOCKING, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.DIPLOCAULUS.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricWaterSpawnRules);
            SpawnPlacements.register(ModEntities.DIPLODOCUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.DODO.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.DRYOSAURUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.EDAPHOSAURUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.ELASMOTHERIUM.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.GALLIMIMUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.GASTORNIS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.HENODUS.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricWaterSpawnRules);
            SpawnPlacements.register(ModEntities.ICHTHYOSAURUS.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricWaterSpawnRules);
            SpawnPlacements.register(ModEntities.KELENKEN.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.LIOPLEURODON.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricWaterSpawnRules);
            SpawnPlacements.register(ModEntities.LONCHODOMAS.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricWaterSpawnRules);
            SpawnPlacements.register(ModEntities.MAMMOTH.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.MEGALANIA.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.MEGALOCEROS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.MEGALODON.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricWaterSpawnRules);
            SpawnPlacements.register(ModEntities.MEGANEURA.get(), ON_GROUND, Heightmap.Types.MOTION_BLOCKING, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.MOSASAURUS.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricWaterSpawnRules);
            SpawnPlacements.register(ModEntities.ORNITHOLESTES.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.PACHYCEPHALOSAURUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.PACHYRHINOSAURUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.PARASAUROLOPHUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.PHORUSRHACOS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.PLATYBELODON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.PLESIOSAURUS.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricWaterSpawnRules);
            SpawnPlacements.register(ModEntities.PROTOCERATOPS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.PSITTACOSAURUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.PTERANODON.get(), ON_GROUND, Heightmap.Types.MOTION_BLOCKING, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.QUAGGA.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.QUETZALCOATLUS.get(), ON_GROUND, Heightmap.Types.MOTION_BLOCKING, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.SARCOSUCHUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.SCOTOHARPES.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricWaterSpawnRules);
            SpawnPlacements.register(ModEntities.SMILODON.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.SPINOSAURUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.STEGOSAURUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.THERIZINOSAURUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.TIKTAALIK.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricWaterSpawnRules);
            SpawnPlacements.register(ModEntities.TITANIS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.TRICERATOPS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.TYRANNOSAURUS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.VELOCIRAPTOR.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricSpawnRules);
            SpawnPlacements.register(ModEntities.WALLISEROPS.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, AncientEarth::checkPrehistoricWaterSpawnRules);
        });
    }
    
    public static boolean checkPrehistoricSpawnRules(EntityType<?> typeIn, LevelAccessor levelAccIn, MobSpawnType spawnTypeIn, BlockPos blockPosIn, RandomSource randomIn) {
        return checkPrehistoricSpawnRules(typeIn, levelAccIn, spawnTypeIn, blockPosIn, randomIn, false);
    }
    
    public static boolean checkPrehistoricSpawnRulesAllowDarkness(EntityType<?> typeIn, LevelAccessor levelAccIn, MobSpawnType spawnTypeIn, BlockPos blockPosIn, RandomSource randomIn) {
        return checkPrehistoricSpawnRules(typeIn, levelAccIn, spawnTypeIn, blockPosIn, randomIn, true);
    }
    
    public static boolean checkPrehistoricSpawnRules(EntityType<?> typeIn, LevelAccessor levelAccIn, MobSpawnType spawnTypeIn, BlockPos blockPosIn, RandomSource randomIn, boolean allowDarknessIn) {
        ResourceLocation entityKey = levelAccIn.registryAccess().registryOrThrow(Registries.ENTITY_TYPE).getKey(typeIn);
        TagKey<Block> entityBlocks = BlockTags.create(new ResourceLocation(MODID, entityKey.getPath() + "_spawnable_on"));
        return ((levelAccIn.getBlockState(blockPosIn.below()).is(entityBlocks) || levelAccIn.getBlockState(blockPosIn.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON)) && (allowDarknessIn || levelAccIn.getRawBrightness(blockPosIn, 0) > 8));
    }
    
    public static boolean checkPrehistoricWaterSpawnRules(EntityType<?> typeIn, LevelAccessor levelAccIn, MobSpawnType spawnTypeIn, BlockPos blockPosIn, RandomSource randomIn) {
        return checkPrehistoricWaterSpawnRules(typeIn, levelAccIn, spawnTypeIn, blockPosIn, randomIn, true);
    }
    
    public static boolean checkPrehistoricWaterAndCaveSpawnRules(EntityType<?> typeIn, LevelAccessor levelAccIn, MobSpawnType spawnTypeIn, BlockPos blockPosIn, RandomSource randomIn) {
        if (levelAccIn.getFluidState(blockPosIn.below()).is(FluidTags.WATER) && levelAccIn.getBlockState(blockPosIn.above()).is(Blocks.WATER) && (levelAccIn.getBiome(blockPosIn).is(Biomes.LUSH_CAVES))) return true;
        return checkPrehistoricWaterSpawnRules(typeIn, levelAccIn, spawnTypeIn, blockPosIn, randomIn, false);
        
    }
    
    @SuppressWarnings("deprecation")
    public static boolean checkPrehistoricWaterSpawnRules(EntityType<?> typeIn, LevelAccessor levelAccIn, MobSpawnType spawnTypeIn, BlockPos blockPosIn, RandomSource randomIn, boolean testRarityIn) {
        int seaLevel = levelAccIn.getSeaLevel();
        int belowSeaLevel = seaLevel - 13;
        return blockPosIn.getY() >= belowSeaLevel && blockPosIn.getY() <= seaLevel && levelAccIn.getFluidState(blockPosIn.below()).is(FluidTags.WATER) && levelAccIn.getBlockState(blockPosIn.above()).is(Blocks.WATER);
    }
    
    public static Logger getLogger() { return LOGGER; }
    
    public class Config {
        public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        public static final ForgeConfigSpec SPEC;
        public static final ForgeConfigSpec.ConfigValue<Boolean> ADVANCED_DEBUGGING;
        
        static {
            BUILDER.push("General Settings");
            ADVANCED_DEBUGGING = BUILDER.comment("Prints additional debug information to the log.").define("advanced_debugging", false);
            BUILDER.pop();
            SPEC = BUILDER.build();
        }
    }

    // --- Data Generation Helpers ---
    
    public static Map<String, List<SpawnerData>> createSpawnerMap() {
        Map<String, List<SpawnerData>> map = new HashMap<>();
        
        // Badlands
        addToMap(map, "badlands", ModEntities.ALLOSAURUS.get(), 60, 1, 2);
        addToMap(map, "badlands", ModEntities.ANKYLOSAURUS.get(), 60, 1, 4);
        addToMap(map, "badlands", ModEntities.CERATOSAURUS.get(), 50, 1, 2);
        addToMap(map, "badlands", ModEntities.CITIPATI.get(), 80, 2, 6);
        addToMap(map, "badlands", ModEntities.DILOPHOSAURUS.get(), 80, 2, 6);
        addToMap(map, "badlands", ModEntities.DRYOSAURUS.get(), 80, 2, 6);
        addToMap(map, "badlands", ModEntities.GALLIMIMUS.get(), 80, 2, 6);
        addToMap(map, "badlands", ModEntities.MEGALANIA.get(), 100, 1, 3);
        addToMap(map, "badlands", ModEntities.ORNITHOLESTES.get(), 60, 2, 5);
        addToMap(map, "badlands", ModEntities.POSTOSUCHUS.get(), 50, 1, 2);
        addToMap(map, "badlands", ModEntities.PROTOCERATOPS.get(), 80, 2, 6);
        addToMap(map, "badlands", ModEntities.PSITTACOSAURUS.get(), 80, 2, 6);
        addToMap(map, "badlands", ModEntities.STEGOSAURUS.get(), 80, 2, 6);
        addToMap(map, "badlands", ModEntities.TYRANNOSAURUS.get(), 20, 1, 2);
        addToMap(map, "badlands", ModEntities.VELOCIRAPTOR.get(), 80, 2, 6);

        // Wooded Badlands
        addToMap(map, "wooded_badlands", ModEntities.ALLOSAURUS.get(), 60, 1, 2);
        addToMap(map, "wooded_badlands", ModEntities.ANKYLOSAURUS.get(), 60, 1, 4);
        addToMap(map, "wooded_badlands", ModEntities.CERATOSAURUS.get(), 50, 1, 2);
        addToMap(map, "wooded_badlands", ModEntities.CITIPATI.get(), 80, 2, 6);
        addToMap(map, "wooded_badlands", ModEntities.DILOPHOSAURUS.get(), 80, 2, 6);
        addToMap(map, "wooded_badlands", ModEntities.DRYOSAURUS.get(), 80, 2, 6);
        addToMap(map, "wooded_badlands", ModEntities.GALLIMIMUS.get(), 80, 2, 6);
        addToMap(map, "wooded_badlands", ModEntities.MEGALANIA.get(), 100, 1, 3);
        addToMap(map, "wooded_badlands", ModEntities.ORNITHOLESTES.get(), 60, 2, 5);
        addToMap(map, "wooded_badlands", ModEntities.POSTOSUCHUS.get(), 50, 1, 2);
        addToMap(map, "wooded_badlands", ModEntities.PROTOCERATOPS.get(), 80, 2, 6);
        addToMap(map, "wooded_badlands", ModEntities.PSITTACOSAURUS.get(), 80, 2, 6);
        addToMap(map, "wooded_badlands", ModEntities.STEGOSAURUS.get(), 80, 2, 6);
        addToMap(map, "wooded_badlands", ModEntities.TYRANNOSAURUS.get(), 20, 1, 2);
        addToMap(map, "wooded_badlands", ModEntities.VELOCIRAPTOR.get(), 80, 2, 6);

        // Beach
        addToMap(map, "beach", ModEntities.CONFUCIUSORNIS.get(), 80, 2, 6);
        addToMap(map, "beach", ModEntities.DILOPHOSAURUS.get(), 60, 1, 4);
        addToMap(map, "beach", ModEntities.DIMORPHODON.get(), 80, 2, 6);
        addToMap(map, "beach", ModEntities.DODO.get(), 100, 3, 8);
        addToMap(map, "beach", ModEntities.GALLIMIMUS.get(), 60, 2, 5);
        addToMap(map, "beach", ModEntities.ICHTHYOSAURUS.get(), 60, 1, 4);
        addToMap(map, "beach", ModEntities.KELENKEN.get(), 60, 1, 3);
        addToMap(map, "beach", ModEntities.PACHYCEPHALOSAURUS.get(), 60, 1, 3);
        addToMap(map, "beach", ModEntities.PTERANODON.get(), 80, 2, 6);
        addToMap(map, "beach", ModEntities.QUETZALCOATLUS.get(), 40, 1, 3);
        addToMap(map, "beach", ModEntities.SARCOSUCHUS.get(), 50, 1, 3);

        // Rocky Beach
        addToMap(map, "rocky_beach", ModEntities.CONFUCIUSORNIS.get(), 80, 2, 6);
        addToMap(map, "rocky_beach", ModEntities.DIMORPHODON.get(), 80, 2, 6);
        addToMap(map, "rocky_beach", ModEntities.DODO.get(), 80, 2, 6);
        addToMap(map, "rocky_beach", ModEntities.ICHTHYOSAURUS.get(), 60, 1, 4);
        addToMap(map, "rocky_beach", ModEntities.KELENKEN.get(), 60, 1, 3);
        addToMap(map, "rocky_beach", ModEntities.PACHYCEPHALOSAURUS.get(), 60, 1, 3);
        addToMap(map, "rocky_beach", ModEntities.PTERANODON.get(), 80, 2, 6);
        addToMap(map, "rocky_beach", ModEntities.QUETZALCOATLUS.get(), 40, 1, 3);

        // Desert
        addToMap(map, "desert", ModEntities.ALLOSAURUS.get(), 60, 1, 2);
        addToMap(map, "desert", ModEntities.ANKYLOSAURUS.get(), 60, 1, 4);
        addToMap(map, "desert", ModEntities.CERATOSAURUS.get(), 50, 1, 2);
        addToMap(map, "desert", ModEntities.CITIPATI.get(), 80, 2, 6);
        addToMap(map, "desert", ModEntities.DILOPHOSAURUS.get(), 80, 2, 6);
        addToMap(map, "desert", ModEntities.DRYOSAURUS.get(), 60, 2, 5);
        addToMap(map, "desert", ModEntities.GALLIMIMUS.get(), 80, 2, 6);
        addToMap(map, "desert", ModEntities.MEGALANIA.get(), 100, 1, 3);
        addToMap(map, "desert", ModEntities.ORNITHOLESTES.get(), 60, 2, 5);
        addToMap(map, "desert", ModEntities.POSTOSUCHUS.get(), 50, 1, 2);
        addToMap(map, "desert", ModEntities.PROTOCERATOPS.get(), 80, 2, 6);
        addToMap(map, "desert", ModEntities.PSITTACOSAURUS.get(), 80, 2, 6);
        addToMap(map, "desert", ModEntities.STEGOSAURUS.get(), 80, 2, 6);
        addToMap(map, "desert", ModEntities.TYRANNOSAURUS.get(), 20, 1, 2);
        addToMap(map, "desert", ModEntities.VELOCIRAPTOR.get(), 80, 2, 6);

        // Red Desert
        addToMap(map, "red_desert", ModEntities.ALLOSAURUS.get(), 60, 1, 2);
        addToMap(map, "red_desert", ModEntities.ANKYLOSAURUS.get(), 60, 1, 4);
        addToMap(map, "red_desert", ModEntities.CERATOSAURUS.get(), 50, 1, 2);
        addToMap(map, "red_desert", ModEntities.CITIPATI.get(), 80, 2, 6);
        addToMap(map, "red_desert", ModEntities.DILOPHOSAURUS.get(), 80, 2, 6);
        addToMap(map, "red_desert", ModEntities.DRYOSAURUS.get(), 60, 2, 5);
        addToMap(map, "red_desert", ModEntities.GALLIMIMUS.get(), 80, 2, 6);
        addToMap(map, "red_desert", ModEntities.MEGALANIA.get(), 100, 1, 3);
        addToMap(map, "red_desert", ModEntities.ORNITHOLESTES.get(), 60, 2, 5);
        addToMap(map, "red_desert", ModEntities.POSTOSUCHUS.get(), 50, 1, 2);
        addToMap(map, "red_desert", ModEntities.PROTOCERATOPS.get(), 80, 2, 6);
        addToMap(map, "red_desert", ModEntities.PSITTACOSAURUS.get(), 80, 2, 6);
        addToMap(map, "red_desert", ModEntities.STEGOSAURUS.get(), 80, 2, 6);
        addToMap(map, "red_desert", ModEntities.TYRANNOSAURUS.get(), 20, 1, 2);
        addToMap(map, "red_desert", ModEntities.VELOCIRAPTOR.get(), 80, 2, 6);

        // Forest
        addToMap(map, "forest", ModEntities.ANKYLOSAURUS.get(), 80, 1, 4);
        addToMap(map, "forest", ModEntities.CERATOSAURUS.get(), 50, 1, 2);
        addToMap(map, "forest", ModEntities.COMPSOGNATHUS.get(), 80, 2, 6);
        addToMap(map, "forest", ModEntities.DEINONYCHUS.get(), 60, 2, 5);
        addToMap(map, "forest", ModEntities.DIMORPHODON.get(), 80, 2, 6);
        addToMap(map, "forest", ModEntities.DRYOSAURUS.get(), 100, 2, 6);
        addToMap(map, "forest", ModEntities.GASTORNIS.get(), 60, 1, 4);
        addToMap(map, "forest", ModEntities.MEGALOCEROS.get(), 80, 2, 6);
        addToMap(map, "forest", ModEntities.ORNITHOLESTES.get(), 80, 2, 5);
        addToMap(map, "forest", ModEntities.PACHYRHINOSAURUS.get(), 80, 2, 5);
        addToMap(map, "forest", ModEntities.PARASAUROLOPHUS.get(), 100, 2, 6);
        addToMap(map, "forest", ModEntities.PHORUSRHACOS.get(), 60, 1, 4);
        addToMap(map, "forest", ModEntities.PSITTACOSAURUS.get(), 80, 2, 6);
        addToMap(map, "forest", ModEntities.SMILODON.get(), 60, 1, 3);
        addToMap(map, "forest", ModEntities.TRICERATOPS.get(), 80, 2, 6);
        addToMap(map, "forest", ModEntities.TYRANNOSAURUS.get(), 20, 1, 2);

        // Autumnal Forest
        addToMap(map, "autumnal_forest", ModEntities.ANKYLOSAURUS.get(), 80, 1, 4);
        addToMap(map, "autumnal_forest", ModEntities.CERATOSAURUS.get(), 50, 1, 2);
        addToMap(map, "autumnal_forest", ModEntities.COMPSOGNATHUS.get(), 80, 2, 6);
        addToMap(map, "autumnal_forest", ModEntities.DEINONYCHUS.get(), 60, 2, 5);
        addToMap(map, "autumnal_forest", ModEntities.DIMORPHODON.get(), 80, 2, 6);
        addToMap(map, "autumnal_forest", ModEntities.DRYOSAURUS.get(), 100, 2, 6);
        addToMap(map, "autumnal_forest", ModEntities.GASTORNIS.get(), 60, 1, 4);
        addToMap(map, "autumnal_forest", ModEntities.MEGALOCEROS.get(), 80, 2, 6);
        addToMap(map, "autumnal_forest", ModEntities.ORNITHOLESTES.get(), 80, 2, 5);
        addToMap(map, "autumnal_forest", ModEntities.PACHYRHINOSAURUS.get(), 80, 2, 5);
        addToMap(map, "autumnal_forest", ModEntities.PARASAUROLOPHUS.get(), 100, 2, 6);
        addToMap(map, "autumnal_forest", ModEntities.PHORUSRHACOS.get(), 60, 1, 4);
        addToMap(map, "autumnal_forest", ModEntities.PSITTACOSAURUS.get(), 80, 2, 6);
        addToMap(map, "autumnal_forest", ModEntities.SMILODON.get(), 60, 1, 3);
        addToMap(map, "autumnal_forest", ModEntities.TRICERATOPS.get(), 80, 2, 6);
        addToMap(map, "autumnal_forest", ModEntities.TYRANNOSAURUS.get(), 20, 1, 2);

        // Birch Forest
        addToMap(map, "birch_forest", ModEntities.ANKYLOSAURUS.get(), 80, 1, 4);
        addToMap(map, "birch_forest", ModEntities.CERATOSAURUS.get(), 50, 1, 2);
        addToMap(map, "birch_forest", ModEntities.COMPSOGNATHUS.get(), 80, 2, 6);
        addToMap(map, "birch_forest", ModEntities.DEINONYCHUS.get(), 60, 2, 5);
        addToMap(map, "birch_forest", ModEntities.DIMORPHODON.get(), 80, 2, 6);
        addToMap(map, "birch_forest", ModEntities.DRYOSAURUS.get(), 100, 2, 6);
        addToMap(map, "birch_forest", ModEntities.GASTORNIS.get(), 60, 1, 4);
        addToMap(map, "birch_forest", ModEntities.MEGALOCEROS.get(), 80, 2, 6);
        addToMap(map, "birch_forest", ModEntities.ORNITHOLESTES.get(), 80, 2, 5);
        addToMap(map, "birch_forest", ModEntities.PACHYRHINOSAURUS.get(), 80, 2, 5);
        addToMap(map, "birch_forest", ModEntities.PARASAUROLOPHUS.get(), 100, 2, 6);
        addToMap(map, "birch_forest", ModEntities.PHORUSRHACOS.get(), 60, 1, 4);
        addToMap(map, "birch_forest", ModEntities.PSITTACOSAURUS.get(), 80, 2, 6);
        addToMap(map, "birch_forest", ModEntities.SMILODON.get(), 60, 1, 3);
        addToMap(map, "birch_forest", ModEntities.TRICERATOPS.get(), 80, 2, 6);
        addToMap(map, "birch_forest", ModEntities.TYRANNOSAURUS.get(), 20, 1, 2);

        // Cherry Forest
        addToMap(map, "cherry_forest", ModEntities.ANKYLOSAURUS.get(), 80, 1, 4);
        addToMap(map, "cherry_forest", ModEntities.COMPSOGNATHUS.get(), 80, 2, 6);
        addToMap(map, "cherry_forest", ModEntities.CONFUCIUSORNIS.get(), 100, 3, 8);
        addToMap(map, "cherry_forest", ModEntities.DIMORPHODON.get(), 80, 2, 6);
        addToMap(map, "cherry_forest", ModEntities.DRYOSAURUS.get(), 100, 2, 6);
        addToMap(map, "cherry_forest", ModEntities.GASTORNIS.get(), 60, 1, 4);
        addToMap(map, "cherry_forest", ModEntities.ORNITHOLESTES.get(), 80, 2, 5);
        addToMap(map, "cherry_forest", ModEntities.PARASAUROLOPHUS.get(), 100, 2, 6);
        addToMap(map, "cherry_forest", ModEntities.PHORUSRHACOS.get(), 60, 1, 4);
        addToMap(map, "cherry_forest", ModEntities.PSITTACOSAURUS.get(), 80, 2, 6);
        addToMap(map, "cherry_forest", ModEntities.TRICERATOPS.get(), 80, 2, 6);

        // Dark Forest
        addToMap(map, "dark_forest", ModEntities.ANKYLOSAURUS.get(), 80, 1, 4);
        addToMap(map, "dark_forest", ModEntities.CERATOSAURUS.get(), 60, 1, 2);
        addToMap(map, "dark_forest", ModEntities.COMPSOGNATHUS.get(), 100, 2, 8);
        addToMap(map, "dark_forest", ModEntities.DEINONYCHUS.get(), 80, 2, 6);
        addToMap(map, "dark_forest", ModEntities.DIMORPHODON.get(), 80, 2, 6);
        addToMap(map, "dark_forest", ModEntities.DRYOSAURUS.get(), 80, 2, 6);
        addToMap(map, "dark_forest", ModEntities.GASTORNIS.get(), 60, 1, 4);
        addToMap(map, "dark_forest", ModEntities.MEGALOCEROS.get(), 60, 2, 5);
        addToMap(map, "dark_forest", ModEntities.ORNITHOLESTES.get(), 80, 2, 5);
        addToMap(map, "dark_forest", ModEntities.PARASAUROLOPHUS.get(), 80, 2, 6);
        addToMap(map, "dark_forest", ModEntities.PHORUSRHACOS.get(), 60, 1, 4);
        addToMap(map, "dark_forest", ModEntities.PSITTACOSAURUS.get(), 80, 2, 6);
        addToMap(map, "dark_forest", ModEntities.SMILODON.get(), 60, 1, 3);
        addToMap(map, "dark_forest", ModEntities.TRICERATOPS.get(), 60, 1, 4);

        // Sparse Forest
        addToMap(map, "sparse_forest", ModEntities.ANKYLOSAURUS.get(), 80, 1, 4);
        addToMap(map, "sparse_forest", ModEntities.BRACHIOSAURUS.get(), 60, 1, 3);
        addToMap(map, "sparse_forest", ModEntities.CERATOSAURUS.get(), 50, 1, 2);
        addToMap(map, "sparse_forest", ModEntities.COMPSOGNATHUS.get(), 80, 2, 6);
        addToMap(map, "sparse_forest", ModEntities.DIMORPHODON.get(), 80, 2, 6);
        addToMap(map, "sparse_forest", ModEntities.DIPLODOCUS.get(), 60, 1, 3);
        addToMap(map, "sparse_forest", ModEntities.DRYOSAURUS.get(), 100, 2, 6);
        addToMap(map, "sparse_forest", ModEntities.GASTORNIS.get(), 60, 1, 4);
        addToMap(map, "sparse_forest", ModEntities.MEGALOCEROS.get(), 80, 2, 6);
        addToMap(map, "sparse_forest", ModEntities.ORNITHOLESTES.get(), 80, 2, 5);
        addToMap(map, "sparse_forest", ModEntities.PACHYRHINOSAURUS.get(), 80, 2, 5);
        addToMap(map, "sparse_forest", ModEntities.PARASAUROLOPHUS.get(), 100, 2, 6);
        addToMap(map, "sparse_forest", ModEntities.PHORUSRHACOS.get(), 60, 1, 4);
        addToMap(map, "sparse_forest", ModEntities.PSITTACOSAURUS.get(), 80, 2, 6);
        addToMap(map, "sparse_forest", ModEntities.TRICERATOPS.get(), 80, 2, 6);
        addToMap(map, "sparse_forest", ModEntities.TYRANNOSAURUS.get(), 20, 1, 2);

        // Frozen
        addToMap(map, "frozen", ModEntities.ELASMOTHERIUM.get(), 100, 2, 6);
        addToMap(map, "frozen", ModEntities.MAMMOTH.get(), 100, 2, 6);
        addToMap(map, "frozen", ModEntities.MEGALOCEROS.get(), 100, 2, 6);
        addToMap(map, "frozen", ModEntities.PACHYRHINOSAURUS.get(), 80, 2, 5);
        addToMap(map, "frozen", ModEntities.SMILODON.get(), 60, 1, 3);
        addToMap(map, "frozen", ModEntities.GASTORNIS.get(), 60, 1, 3);

        // Tundra
        addToMap(map, "tundra", ModEntities.ELASMOTHERIUM.get(), 100, 2, 6);
        addToMap(map, "tundra", ModEntities.MAMMOTH.get(), 100, 2, 6);
        addToMap(map, "tundra", ModEntities.MEGALOCEROS.get(), 100, 2, 6);
        addToMap(map, "tundra", ModEntities.PACHYRHINOSAURUS.get(), 80, 2, 5);
        addToMap(map, "tundra", ModEntities.SMILODON.get(), 60, 1, 3);
        addToMap(map, "tundra", ModEntities.GASTORNIS.get(), 60, 1, 3);

        // Mountain
        addToMap(map, "mountain", ModEntities.CERATOSAURUS.get(), 60, 1, 3);
        addToMap(map, "mountain", ModEntities.ELASMOTHERIUM.get(), 60, 1, 3);
        addToMap(map, "mountain", ModEntities.KELENKEN.get(), 80, 2, 5);
        addToMap(map, "mountain", ModEntities.MEGALOCEROS.get(), 80, 2, 5);
        addToMap(map, "mountain", ModEntities.PACHYCEPHALOSAURUS.get(), 80, 2, 5);
        addToMap(map, "mountain", ModEntities.SMILODON.get(), 80, 1, 4);
        addToMap(map, "mountain", ModEntities.TITANIS.get(), 60, 1, 3);
        addToMap(map, "mountain", ModEntities.TRICERATOPS.get(), 60, 2, 5);

        // Snowy Mountain
        addToMap(map, "snowy_mountain", ModEntities.ELASMOTHERIUM.get(), 80, 1, 4);
        addToMap(map, "snowy_mountain", ModEntities.KELENKEN.get(), 60, 1, 3);
        addToMap(map, "snowy_mountain", ModEntities.MAMMOTH.get(), 80, 2, 5);
        addToMap(map, "snowy_mountain", ModEntities.MEGALOCEROS.get(), 80, 2, 5);
        addToMap(map, "snowy_mountain", ModEntities.PACHYCEPHALOSAURUS.get(), 60, 1, 3);
        addToMap(map, "snowy_mountain", ModEntities.SMILODON.get(), 80, 1, 4);

        // Plains
        addToMap(map, "plains", ModEntities.ANKYLOSAURUS.get(), 60, 1, 4);
        addToMap(map, "plains", ModEntities.BRACHIOSAURUS.get(), 60, 1, 3);
        addToMap(map, "plains", ModEntities.CERATOSAURUS.get(), 50, 1, 2);
        addToMap(map, "plains", ModEntities.CONFUCIUSORNIS.get(), 100, 3, 8);
        addToMap(map, "plains", ModEntities.DIPLODOCUS.get(), 80, 2, 5);
        addToMap(map, "plains", ModEntities.DRYOSAURUS.get(), 100, 2, 6);
        addToMap(map, "plains", ModEntities.GALLIMIMUS.get(), 100, 2, 6);
        addToMap(map, "plains", ModEntities.ORNITHOLESTES.get(), 80, 2, 5);
        addToMap(map, "plains", ModEntities.PARASAUROLOPHUS.get(), 100, 2, 6);
        addToMap(map, "plains", ModEntities.PHORUSRHACOS.get(), 60, 1, 4);
        addToMap(map, "plains", ModEntities.QUAGGA.get(), 80, 2, 6);
        addToMap(map, "plains", ModEntities.STEGOSAURUS.get(), 80, 2, 5);
        addToMap(map, "plains", ModEntities.THERIZINOSAURUS.get(), 60, 1, 3);
        addToMap(map, "plains", ModEntities.TITANIS.get(), 60, 1, 4);
        addToMap(map, "plains", ModEntities.TRICERATOPS.get(), 80, 2, 6);
        addToMap(map, "plains", ModEntities.TYRANNOSAURUS.get(), 20, 1, 2);

        // Floral Fields
        addToMap(map, "floral_fields", ModEntities.ANKYLOSAURUS.get(), 60, 1, 4);
        addToMap(map, "floral_fields", ModEntities.BRACHIOSAURUS.get(), 60, 1, 3);
        addToMap(map, "floral_fields", ModEntities.CONFUCIUSORNIS.get(), 100, 3, 8);
        addToMap(map, "floral_fields", ModEntities.DIPLODOCUS.get(), 80, 2, 5);
        addToMap(map, "floral_fields", ModEntities.DRYOSAURUS.get(), 100, 2, 6);
        addToMap(map, "floral_fields", ModEntities.GALLIMIMUS.get(), 100, 2, 6);
        addToMap(map, "floral_fields", ModEntities.ORNITHOLESTES.get(), 80, 2, 5);
        addToMap(map, "floral_fields", ModEntities.PARASAUROLOPHUS.get(), 100, 2, 6);
        addToMap(map, "floral_fields", ModEntities.PHORUSRHACOS.get(), 60, 1, 4);
        addToMap(map, "floral_fields", ModEntities.QUAGGA.get(), 80, 2, 6);
        addToMap(map, "floral_fields", ModEntities.STEGOSAURUS.get(), 80, 2, 5);
        addToMap(map, "floral_fields", ModEntities.TITANIS.get(), 60, 1, 4);
        addToMap(map, "floral_fields", ModEntities.TRICERATOPS.get(), 80, 2, 6);
        addToMap(map, "floral_fields", ModEntities.TYRANNOSAURUS.get(), 20, 1, 2);

        // Highlands
        addToMap(map, "highlands", ModEntities.CERATOSAURUS.get(), 60, 1, 3);
        addToMap(map, "highlands", ModEntities.CONFUCIUSORNIS.get(), 80, 2, 6);
        addToMap(map, "highlands", ModEntities.DIPLODOCUS.get(), 60, 1, 3);
        addToMap(map, "highlands", ModEntities.DRYOSAURUS.get(), 80, 2, 5);
        addToMap(map, "highlands", ModEntities.GALLIMIMUS.get(), 80, 2, 5);
        addToMap(map, "highlands", ModEntities.KELENKEN.get(), 60, 1, 3);
        addToMap(map, "highlands", ModEntities.MEGALOCEROS.get(), 80, 2, 5);
        addToMap(map, "highlands", ModEntities.PACHYCEPHALOSAURUS.get(), 80, 2, 5);
        addToMap(map, "highlands", ModEntities.PARASAUROLOPHUS.get(), 80, 2, 5);
        addToMap(map, "highlands", ModEntities.PHORUSRHACOS.get(), 60, 1, 4);
        addToMap(map, "highlands", ModEntities.QUAGGA.get(), 80, 2, 5);
        addToMap(map, "highlands", ModEntities.SMILODON.get(), 60, 1, 3);
        addToMap(map, "highlands", ModEntities.TITANIS.get(), 60, 1, 4);
        addToMap(map, "highlands", ModEntities.TRICERATOPS.get(), 60, 2, 5);

        // Prairie
        addToMap(map, "prairie", ModEntities.ANKYLOSAURUS.get(), 60, 1, 4);
        addToMap(map, "prairie", ModEntities.BRACHIOSAURUS.get(), 60, 1, 3);
        addToMap(map, "prairie", ModEntities.CERATOSAURUS.get(), 50, 1, 2);
        addToMap(map, "prairie", ModEntities.CONFUCIUSORNIS.get(), 100, 3, 8);
        addToMap(map, "prairie", ModEntities.DIPLODOCUS.get(), 80, 2, 5);
        addToMap(map, "prairie", ModEntities.DRYOSAURUS.get(), 100, 2, 6);
        addToMap(map, "prairie", ModEntities.GALLIMIMUS.get(), 100, 2, 6);
        addToMap(map, "prairie", ModEntities.ORNITHOLESTES.get(), 80, 2, 5);
        addToMap(map, "prairie", ModEntities.PARASAUROLOPHUS.get(), 100, 2, 6);
        addToMap(map, "prairie", ModEntities.PHORUSRHACOS.get(), 60, 1, 4);
        addToMap(map, "prairie", ModEntities.QUAGGA.get(), 80, 2, 6);
        addToMap(map, "prairie", ModEntities.STEGOSAURUS.get(), 80, 2, 5);
        addToMap(map, "prairie", ModEntities.THERIZINOSAURUS.get(), 60, 1, 3);
        addToMap(map, "prairie", ModEntities.TITANIS.get(), 60, 1, 4);
        addToMap(map, "prairie", ModEntities.TRICERATOPS.get(), 80, 2, 6);
        addToMap(map, "prairie", ModEntities.TYRANNOSAURUS.get(), 20, 1, 2);

        // Savanna
        addToMap(map, "savanna", ModEntities.ALLOSAURUS.get(), 50, 1, 2);
        addToMap(map, "savanna", ModEntities.ANKYLOSAURUS.get(), 60, 1, 4);
        addToMap(map, "savanna", ModEntities.BRACHIOSAURUS.get(), 80, 2, 5);
        addToMap(map, "savanna", ModEntities.CERATOSAURUS.get(), 50, 1, 2);
        addToMap(map, "savanna", ModEntities.CITIPATI.get(), 80, 2, 6);
        addToMap(map, "savanna", ModEntities.DIPLODOCUS.get(), 60, 1, 3);
        addToMap(map, "savanna", ModEntities.GALLIMIMUS.get(), 100, 2, 6);
        addToMap(map, "savanna", ModEntities.MEGALANIA.get(), 60, 1, 3);
        addToMap(map, "savanna", ModEntities.PLATYBELODON.get(), 80, 2, 5);
        addToMap(map, "savanna", ModEntities.PSITTACOSAURUS.get(), 80, 2, 6);
        addToMap(map, "savanna", ModEntities.QUAGGA.get(), 80, 2, 6);
        addToMap(map, "savanna", ModEntities.STEGOSAURUS.get(), 80, 2, 6);
        addToMap(map, "savanna", ModEntities.THERIZINOSAURUS.get(), 60, 1, 3);
        addToMap(map, "savanna", ModEntities.TRICERATOPS.get(), 80, 2, 6);
        addToMap(map, "savanna", ModEntities.TYRANNOSAURUS.get(), 20, 1, 2);
        addToMap(map, "savanna", ModEntities.VELOCIRAPTOR.get(), 80, 2, 6);

        // Sparse Savanna
        addToMap(map, "sparse_savanna", ModEntities.ALLOSAURUS.get(), 50, 1, 2);
        addToMap(map, "sparse_savanna", ModEntities.ANKYLOSAURUS.get(), 60, 1, 4);
        addToMap(map, "sparse_savanna", ModEntities.BRACHIOSAURUS.get(), 80, 2, 5);
        addToMap(map, "sparse_savanna", ModEntities.CERATOSAURUS.get(), 50, 1, 2);
        addToMap(map, "sparse_savanna", ModEntities.CITIPATI.get(), 80, 2, 6);
        addToMap(map, "sparse_savanna", ModEntities.DIPLODOCUS.get(), 60, 1, 3);
        addToMap(map, "sparse_savanna", ModEntities.GALLIMIMUS.get(), 100, 2, 6);
        addToMap(map, "sparse_savanna", ModEntities.MEGALANIA.get(), 60, 1, 3);
        addToMap(map, "sparse_savanna", ModEntities.PLATYBELODON.get(), 80, 2, 5);
        addToMap(map, "sparse_savanna", ModEntities.PSITTACOSAURUS.get(), 80, 2, 6);
        addToMap(map, "sparse_savanna", ModEntities.QUAGGA.get(), 80, 2, 6);
        addToMap(map, "sparse_savanna", ModEntities.STEGOSAURUS.get(), 80, 2, 6);
        addToMap(map, "sparse_savanna", ModEntities.THERIZINOSAURUS.get(), 60, 1, 3);
        addToMap(map, "sparse_savanna", ModEntities.TRICERATOPS.get(), 80, 2, 6);
        addToMap(map, "sparse_savanna", ModEntities.TYRANNOSAURUS.get(), 20, 1, 2);
        addToMap(map, "sparse_savanna", ModEntities.VELOCIRAPTOR.get(), 80, 2, 6);

        // Dense Savanna
        addToMap(map, "dense_savanna", ModEntities.ALLOSAURUS.get(), 50, 1, 2);
        addToMap(map, "dense_savanna", ModEntities.BRACHIOSAURUS.get(), 80, 2, 5);
        addToMap(map, "dense_savanna", ModEntities.CITIPATI.get(), 80, 2, 6);
        addToMap(map, "dense_savanna", ModEntities.GALLIMIMUS.get(), 100, 2, 6);
        addToMap(map, "dense_savanna", ModEntities.MEGALANIA.get(), 60, 1, 3);
        addToMap(map, "dense_savanna", ModEntities.PLATYBELODON.get(), 80, 2, 5);
        addToMap(map, "dense_savanna", ModEntities.PSITTACOSAURUS.get(), 80, 2, 6);
        addToMap(map, "dense_savanna", ModEntities.QUAGGA.get(), 80, 2, 6);
        addToMap(map, "dense_savanna", ModEntities.STEGOSAURUS.get(), 80, 2, 6);
        addToMap(map, "dense_savanna", ModEntities.THERIZINOSAURUS.get(), 60, 1, 3);
        addToMap(map, "dense_savanna", ModEntities.TRICERATOPS.get(), 80, 2, 6);
        addToMap(map, "dense_savanna", ModEntities.TYRANNOSAURUS.get(), 20, 1, 2);
        addToMap(map, "dense_savanna", ModEntities.VELOCIRAPTOR.get(), 80, 2, 6);

        // Seasonal
        addToMap(map, "seasonal", ModEntities.ANKYLOSAURUS.get(), 60, 1, 4);
        addToMap(map, "seasonal", ModEntities.BRACHIOSAURUS.get(), 60, 1, 3);
        addToMap(map, "seasonal", ModEntities.CERATOSAURUS.get(), 50, 1, 2);
        addToMap(map, "seasonal", ModEntities.CONFUCIUSORNIS.get(), 80, 2, 6);
        addToMap(map, "seasonal", ModEntities.DIPLODOCUS.get(), 60, 1, 3);
        addToMap(map, "seasonal", ModEntities.DRYOSAURUS.get(), 100, 2, 6);
        addToMap(map, "seasonal", ModEntities.GALLIMIMUS.get(), 100, 2, 6);
        addToMap(map, "seasonal", ModEntities.ORNITHOLESTES.get(), 80, 2, 5);
        addToMap(map, "seasonal", ModEntities.PARASAUROLOPHUS.get(), 100, 2, 6);
        addToMap(map, "seasonal", ModEntities.PSITTACOSAURUS.get(), 80, 2, 6);
        addToMap(map, "seasonal", ModEntities.STEGOSAURUS.get(), 80, 2, 6);
        addToMap(map, "seasonal", ModEntities.TRICERATOPS.get(), 80, 2, 6);
        addToMap(map, "seasonal", ModEntities.TYRANNOSAURUS.get(), 20, 1, 2);
        addToMap(map, "seasonal", ModEntities.VELOCIRAPTOR.get(), 80, 2, 6);

        // Dense Seasonal
        addToMap(map, "dense_seasonal", ModEntities.ANKYLOSAURUS.get(), 60, 1, 4);
        addToMap(map, "dense_seasonal", ModEntities.BRACHIOSAURUS.get(), 60, 1, 3);
        addToMap(map, "dense_seasonal", ModEntities.CERATOSAURUS.get(), 50, 1, 2);
        addToMap(map, "dense_seasonal", ModEntities.COMPSOGNATHUS.get(), 80, 2, 6);
        addToMap(map, "dense_seasonal", ModEntities.DEINONYCHUS.get(), 60, 2, 5);
        addToMap(map, "dense_seasonal", ModEntities.DRYOSAURUS.get(), 100, 2, 6);
        addToMap(map, "dense_seasonal", ModEntities.GASTORNIS.get(), 60, 1, 4);
        addToMap(map, "dense_seasonal", ModEntities.ORNITHOLESTES.get(), 80, 2, 5);
        addToMap(map, "dense_seasonal", ModEntities.PARASAUROLOPHUS.get(), 100, 2, 6);
        addToMap(map, "dense_seasonal", ModEntities.PSITTACOSAURUS.get(), 80, 2, 6);
        addToMap(map, "dense_seasonal", ModEntities.STEGOSAURUS.get(), 60, 1, 4);
        addToMap(map, "dense_seasonal", ModEntities.TRICERATOPS.get(), 60, 1, 4);
        addToMap(map, "dense_seasonal", ModEntities.THERIZINOSAURUS.get(), 60, 1, 3);

        // Dense Semi Arid
        addToMap(map, "dense_semi_arid", ModEntities.ALLOSAURUS.get(), 60, 1, 2);
        addToMap(map, "dense_semi_arid", ModEntities.ANKYLOSAURUS.get(), 60, 1, 4);
        addToMap(map, "dense_semi_arid", ModEntities.CERATOSAURUS.get(), 50, 1, 2);
        addToMap(map, "dense_semi_arid", ModEntities.CITIPATI.get(), 80, 2, 6);
        addToMap(map, "dense_semi_arid", ModEntities.DILOPHOSAURUS.get(), 80, 2, 6);
        addToMap(map, "dense_semi_arid", ModEntities.GALLIMIMUS.get(), 100, 2, 6);
        addToMap(map, "dense_semi_arid", ModEntities.MEGALANIA.get(), 60, 1, 3);
        addToMap(map, "dense_semi_arid", ModEntities.PLATYBELODON.get(), 60, 1, 4);
        addToMap(map, "dense_semi_arid", ModEntities.POSTOSUCHUS.get(), 50, 1, 2);
        addToMap(map, "dense_semi_arid", ModEntities.PROTOCERATOPS.get(), 80, 2, 6);
        addToMap(map, "dense_semi_arid", ModEntities.PSITTACOSAURUS.get(), 80, 2, 6);
        addToMap(map, "dense_semi_arid", ModEntities.STEGOSAURUS.get(), 80, 2, 6);
        addToMap(map, "dense_semi_arid", ModEntities.THERIZINOSAURUS.get(), 60, 1, 3);
        addToMap(map, "dense_semi_arid", ModEntities.TYRANNOSAURUS.get(), 20, 1, 2);
        addToMap(map, "dense_semi_arid", ModEntities.VELOCIRAPTOR.get(), 80, 2, 6);

        // Sparse Semi Arid
        addToMap(map, "sparse_semi_arid", ModEntities.ALLOSAURUS.get(), 60, 1, 2);
        addToMap(map, "sparse_semi_arid", ModEntities.ANKYLOSAURUS.get(), 60, 1, 4);
        addToMap(map, "sparse_semi_arid", ModEntities.BRACHIOSAURUS.get(), 80, 2, 5);
        addToMap(map, "sparse_semi_arid", ModEntities.CERATOSAURUS.get(), 50, 1, 2);
        addToMap(map, "sparse_semi_arid", ModEntities.CITIPATI.get(), 80, 2, 6);
        addToMap(map, "sparse_semi_arid", ModEntities.DILOPHOSAURUS.get(), 80, 2, 6);
        addToMap(map, "sparse_semi_arid", ModEntities.DIPLODOCUS.get(), 80, 2, 5);
        addToMap(map, "sparse_semi_arid", ModEntities.GALLIMIMUS.get(), 100, 2, 6);
        addToMap(map, "sparse_semi_arid", ModEntities.MEGALANIA.get(), 60, 1, 3);
        addToMap(map, "sparse_semi_arid", ModEntities.PLATYBELODON.get(), 60, 1, 4);
        addToMap(map, "sparse_semi_arid", ModEntities.PROTOCERATOPS.get(), 80, 2, 6);
        addToMap(map, "sparse_semi_arid", ModEntities.PSITTACOSAURUS.get(), 80, 2, 6);
        addToMap(map, "sparse_semi_arid", ModEntities.STEGOSAURUS.get(), 80, 2, 6);
        addToMap(map, "sparse_semi_arid", ModEntities.THERIZINOSAURUS.get(), 60, 1, 3);
        addToMap(map, "sparse_semi_arid", ModEntities.TYRANNOSAURUS.get(), 20, 1, 2);
        addToMap(map, "sparse_semi_arid", ModEntities.VELOCIRAPTOR.get(), 80, 2, 6);

        // Swamp
        addToMap(map, "swamp", ModEntities.ANKYLOSAURUS.get(), 60, 1, 4);
        addToMap(map, "swamp", ModEntities.CERATOSAURUS.get(), 60, 1, 2);
        addToMap(map, "swamp", ModEntities.CONFUCIUSORNIS.get(), 80, 2, 6);
        addToMap(map, "swamp", ModEntities.CRASSIGYRINUS.get(), 80, 1, 3);
        addToMap(map, "swamp", ModEntities.DIMETRODON.get(), 80, 2, 5);
        addToMap(map, "swamp", ModEntities.DIMORPHODON.get(), 80, 2, 6);
        addToMap(map, "swamp", ModEntities.DIPLOCAULUS.get(), 80, 1, 3);
        addToMap(map, "swamp", ModEntities.EDAPHOSAURUS.get(), 80, 2, 5);
        addToMap(map, "swamp", ModEntities.GALLIMIMUS.get(), 60, 2, 5);
        addToMap(map, "swamp", ModEntities.HENODUS.get(), 60, 1, 3);
        addToMap(map, "swamp", ModEntities.MEGANEURA.get(), 100, 3, 8);
        addToMap(map, "swamp", ModEntities.PACHYRHINOSAURUS.get(), 60, 2, 5);
        addToMap(map, "swamp", ModEntities.PARASAUROLOPHUS.get(), 80, 2, 6);
        addToMap(map, "swamp", ModEntities.POSTOSUCHUS.get(), 60, 1, 2);
        addToMap(map, "swamp", ModEntities.QUETZALCOATLUS.get(), 40, 1, 3);
        addToMap(map, "swamp", ModEntities.SARCOSUCHUS.get(), 80, 1, 4);
        addToMap(map, "swamp", ModEntities.SPINOSAURUS.get(), 60, 1, 2);
        addToMap(map, "swamp", ModEntities.TIKTAALIK.get(), 80, 1, 4);

        // Marsh
        addToMap(map, "marsh", ModEntities.ANKYLOSAURUS.get(), 60, 1, 4);
        addToMap(map, "marsh", ModEntities.CERATOSAURUS.get(), 60, 1, 2);
        addToMap(map, "marsh", ModEntities.CONFUCIUSORNIS.get(), 80, 2, 6);
        addToMap(map, "marsh", ModEntities.CRASSIGYRINUS.get(), 80, 1, 3);
        addToMap(map, "marsh", ModEntities.DIMETRODON.get(), 80, 2, 5);
        addToMap(map, "marsh", ModEntities.DIMORPHODON.get(), 80, 2, 6);
        addToMap(map, "marsh", ModEntities.DIPLOCAULUS.get(), 80, 1, 3);
        addToMap(map, "marsh", ModEntities.EDAPHOSAURUS.get(), 80, 2, 5);
        addToMap(map, "marsh", ModEntities.GALLIMIMUS.get(), 80, 2, 5);
        addToMap(map, "marsh", ModEntities.HENODUS.get(), 60, 1, 3);
        addToMap(map, "marsh", ModEntities.MEGANEURA.get(), 100, 3, 8);
        addToMap(map, "marsh", ModEntities.PACHYRHINOSAURUS.get(), 60, 2, 5);
        addToMap(map, "marsh", ModEntities.PARASAUROLOPHUS.get(), 80, 2, 6);
        addToMap(map, "marsh", ModEntities.POSTOSUCHUS.get(), 60, 1, 2);
        addToMap(map, "marsh", ModEntities.QUETZALCOATLUS.get(), 40, 1, 3);
        addToMap(map, "marsh", ModEntities.SARCOSUCHUS.get(), 80, 1, 4);
        addToMap(map, "marsh", ModEntities.SPINOSAURUS.get(), 60, 1, 2);
        addToMap(map, "marsh", ModEntities.TIKTAALIK.get(), 80, 1, 4);

        // Waterlogged Swamp
        addToMap(map, "waterlogged_swamp", ModEntities.CERATOSAURUS.get(), 60, 1, 2);
        addToMap(map, "waterlogged_swamp", ModEntities.CONFUCIUSORNIS.get(), 80, 2, 6);
        addToMap(map, "waterlogged_swamp", ModEntities.CRASSIGYRINUS.get(), 80, 1, 3);
        addToMap(map, "waterlogged_swamp", ModEntities.DIMETRODON.get(), 80, 2, 5);
        addToMap(map, "waterlogged_swamp", ModEntities.DIMORPHODON.get(), 80, 2, 6);
        addToMap(map, "waterlogged_swamp", ModEntities.DIPLOCAULUS.get(), 100, 1, 4);
        addToMap(map, "waterlogged_swamp", ModEntities.EDAPHOSAURUS.get(), 80, 2, 5);
        addToMap(map, "waterlogged_swamp", ModEntities.HENODUS.get(), 80, 1, 3);
        addToMap(map, "waterlogged_swamp", ModEntities.MEGANEURA.get(), 100, 3, 8);
        addToMap(map, "waterlogged_swamp", ModEntities.POSTOSUCHUS.get(), 60, 1, 2);
        addToMap(map, "waterlogged_swamp", ModEntities.SARCOSUCHUS.get(), 100, 1, 4);
        addToMap(map, "waterlogged_swamp", ModEntities.SPINOSAURUS.get(), 60, 1, 2);
        addToMap(map, "waterlogged_swamp", ModEntities.TIKTAALIK.get(), 80, 1, 4);

        // Taiga
        addToMap(map, "taiga", ModEntities.ANKYLOSAURUS.get(), 60, 1, 4);
        addToMap(map, "taiga", ModEntities.CERATOSAURUS.get(), 50, 1, 2);
        addToMap(map, "taiga", ModEntities.DRYOSAURUS.get(), 80, 2, 5);
        addToMap(map, "taiga", ModEntities.ELASMOTHERIUM.get(), 80, 2, 5);
        addToMap(map, "taiga", ModEntities.GASTORNIS.get(), 60, 1, 4);
        addToMap(map, "taiga", ModEntities.MAMMOTH.get(), 80, 2, 5);
        addToMap(map, "taiga", ModEntities.MEGALOCEROS.get(), 100, 2, 6);
        addToMap(map, "taiga", ModEntities.ORNITHOLESTES.get(), 60, 2, 5);
        addToMap(map, "taiga", ModEntities.PACHYRHINOSAURUS.get(), 80, 2, 5);
        addToMap(map, "taiga", ModEntities.PARASAUROLOPHUS.get(), 80, 2, 5);
        addToMap(map, "taiga", ModEntities.SMILODON.get(), 80, 1, 4);
        addToMap(map, "taiga", ModEntities.TRICERATOPS.get(), 80, 2, 6);

        // Mega Taiga
        addToMap(map, "mega_taiga", ModEntities.ANKYLOSAURUS.get(), 60, 1, 4);
        addToMap(map, "mega_taiga", ModEntities.BRACHIOSAURUS.get(), 60, 1, 3);
        addToMap(map, "mega_taiga", ModEntities.CERATOSAURUS.get(), 50, 1, 2);
        addToMap(map, "mega_taiga", ModEntities.DRYOSAURUS.get(), 80, 2, 5);
        addToMap(map, "mega_taiga", ModEntities.ELASMOTHERIUM.get(), 80, 2, 5);
        addToMap(map, "mega_taiga", ModEntities.GASTORNIS.get(), 60, 1, 4);
        addToMap(map, "mega_taiga", ModEntities.MAMMOTH.get(), 80, 2, 5);
        addToMap(map, "mega_taiga", ModEntities.MEGALOCEROS.get(), 100, 2, 6);
        addToMap(map, "mega_taiga", ModEntities.ORNITHOLESTES.get(), 60, 2, 5);
        addToMap(map, "mega_taiga", ModEntities.PACHYRHINOSAURUS.get(), 80, 2, 5);
        addToMap(map, "mega_taiga", ModEntities.PARASAUROLOPHUS.get(), 80, 2, 5);
        addToMap(map, "mega_taiga", ModEntities.SMILODON.get(), 80, 1, 4);
        addToMap(map, "mega_taiga", ModEntities.TRICERATOPS.get(), 80, 2, 6);

        // Snowy Taiga
        addToMap(map, "snowy_taiga", ModEntities.ELASMOTHERIUM.get(), 80, 2, 5);
        addToMap(map, "snowy_taiga", ModEntities.GASTORNIS.get(), 60, 1, 4);
        addToMap(map, "snowy_taiga", ModEntities.MAMMOTH.get(), 80, 2, 5);
        addToMap(map, "snowy_taiga", ModEntities.MEGALOCEROS.get(), 100, 2, 6);
        addToMap(map, "snowy_taiga", ModEntities.PACHYRHINOSAURUS.get(), 80, 2, 5);
        addToMap(map, "snowy_taiga", ModEntities.PARASAUROLOPHUS.get(), 60, 2, 5);
        addToMap(map, "snowy_taiga", ModEntities.SMILODON.get(), 80, 1, 4);
        addToMap(map, "snowy_taiga", ModEntities.TRICERATOPS.get(), 60, 2, 5);

        // Sparse Taiga
        addToMap(map, "sparse_taiga", ModEntities.ANKYLOSAURUS.get(), 60, 1, 4);
        addToMap(map, "sparse_taiga", ModEntities.CERATOSAURUS.get(), 50, 1, 2);
        addToMap(map, "sparse_taiga", ModEntities.DRYOSAURUS.get(), 80, 2, 5);
        addToMap(map, "sparse_taiga", ModEntities.ELASMOTHERIUM.get(), 80, 2, 5);
        addToMap(map, "sparse_taiga", ModEntities.GASTORNIS.get(), 60, 1, 4);
        addToMap(map, "sparse_taiga", ModEntities.MAMMOTH.get(), 80, 2, 5);
        addToMap(map, "sparse_taiga", ModEntities.MEGALOCEROS.get(), 100, 2, 6);
        addToMap(map, "sparse_taiga", ModEntities.PACHYRHINOSAURUS.get(), 80, 2, 5);
        addToMap(map, "sparse_taiga", ModEntities.PARASAUROLOPHUS.get(), 80, 2, 5);
        addToMap(map, "sparse_taiga", ModEntities.SMILODON.get(), 80, 1, 4);
        addToMap(map, "sparse_taiga", ModEntities.TRICERATOPS.get(), 80, 2, 6);

        // Redwood
        addToMap(map, "redwood", ModEntities.ANKYLOSAURUS.get(), 60, 1, 4);
        addToMap(map, "redwood", ModEntities.BRACHIOSAURUS.get(), 80, 1, 3);
        addToMap(map, "redwood", ModEntities.CERATOSAURUS.get(), 50, 1, 2);
        addToMap(map, "redwood", ModEntities.DRYOSAURUS.get(), 80, 2, 5);
        addToMap(map, "redwood", ModEntities.ELASMOTHERIUM.get(), 80, 2, 5);
        addToMap(map, "redwood", ModEntities.GASTORNIS.get(), 60, 1, 4);
        addToMap(map, "redwood", ModEntities.MAMMOTH.get(), 80, 2, 5);
        addToMap(map, "redwood", ModEntities.MEGALOCEROS.get(), 100, 2, 6);
        addToMap(map, "redwood", ModEntities.PACHYRHINOSAURUS.get(), 80, 2, 5);
        addToMap(map, "redwood", ModEntities.PARASAUROLOPHUS.get(), 80, 2, 5);
        addToMap(map, "redwood", ModEntities.SMILODON.get(), 80, 1, 4);
        addToMap(map, "redwood", ModEntities.TRICERATOPS.get(), 80, 2, 6);

        // Tropical
        addToMap(map, "tropical", ModEntities.ARTHROPLEURA.get(), 100, 2, 6);
        addToMap(map, "tropical", ModEntities.BRACHIOSAURUS.get(), 60, 1, 3);
        addToMap(map, "tropical", ModEntities.CERATOSAURUS.get(), 60, 1, 2);
        addToMap(map, "tropical", ModEntities.COMPSOGNATHUS.get(), 100, 3, 8);
        addToMap(map, "tropical", ModEntities.DEINONYCHUS.get(), 80, 2, 6);
        addToMap(map, "tropical", ModEntities.DILOPHOSAURUS.get(), 80, 2, 5);
        addToMap(map, "tropical", ModEntities.DODO.get(), 100, 3, 8);
        addToMap(map, "tropical", ModEntities.EDAPHOSAURUS.get(), 60, 2, 5);
        addToMap(map, "tropical", ModEntities.GALLIMIMUS.get(), 80, 2, 5);
        addToMap(map, "tropical", ModEntities.MEGANEURA.get(), 100, 3, 8);
        addToMap(map, "tropical", ModEntities.ORNITHOLESTES.get(), 80, 2, 5);
        addToMap(map, "tropical", ModEntities.PACHYCEPHALOSAURUS.get(), 80, 2, 4);
        addToMap(map, "tropical", ModEntities.PSITTACOSAURUS.get(), 80, 2, 6);
        addToMap(map, "tropical", ModEntities.QUETZALCOATLUS.get(), 40, 1, 3);
        addToMap(map, "tropical", ModEntities.SPINOSAURUS.get(), 50, 1, 2);
        addToMap(map, "tropical", ModEntities.THERIZINOSAURUS.get(), 60, 1, 3);

        // Sparse Tropical
        addToMap(map, "sparse_tropical", ModEntities.ANKYLOSAURUS.get(), 60, 1, 4);
        addToMap(map, "sparse_tropical", ModEntities.ARTHROPLEURA.get(), 100, 2, 6);
        addToMap(map, "sparse_tropical", ModEntities.BRACHIOSAURUS.get(), 60, 1, 3);
        addToMap(map, "sparse_tropical", ModEntities.CERATOSAURUS.get(), 60, 1, 2);
        addToMap(map, "sparse_tropical", ModEntities.COMPSOGNATHUS.get(), 100, 3, 8);
        addToMap(map, "sparse_tropical", ModEntities.DEINONYCHUS.get(), 80, 2, 6);
        addToMap(map, "sparse_tropical", ModEntities.DILOPHOSAURUS.get(), 80, 2, 5);
        addToMap(map, "sparse_tropical", ModEntities.DIPLODOCUS.get(), 60, 1, 3);
        addToMap(map, "sparse_tropical", ModEntities.DODO.get(), 100, 3, 8);
        addToMap(map, "sparse_tropical", ModEntities.EDAPHOSAURUS.get(), 60, 2, 5);
        addToMap(map, "sparse_tropical", ModEntities.GALLIMIMUS.get(), 80, 2, 5);
        addToMap(map, "sparse_tropical", ModEntities.MEGANEURA.get(), 100, 3, 8);
        addToMap(map, "sparse_tropical", ModEntities.ORNITHOLESTES.get(), 80, 2, 5);
        addToMap(map, "sparse_tropical", ModEntities.PACHYCEPHALOSAURUS.get(), 80, 2, 4);
        addToMap(map, "sparse_tropical", ModEntities.PSITTACOSAURUS.get(), 80, 2, 6);
        addToMap(map, "sparse_tropical", ModEntities.QUETZALCOATLUS.get(), 40, 1, 3);
        addToMap(map, "sparse_tropical", ModEntities.THERIZINOSAURUS.get(), 60, 1, 3);

        // Dense Tropical
        addToMap(map, "dense_tropical", ModEntities.ARTHROPLEURA.get(), 100, 2, 6);
        addToMap(map, "dense_tropical", ModEntities.BRACHIOSAURUS.get(), 60, 1, 3);
        addToMap(map, "dense_tropical", ModEntities.CERATOSAURUS.get(), 60, 1, 2);
        addToMap(map, "dense_tropical", ModEntities.COMPSOGNATHUS.get(), 100, 3, 8);
        addToMap(map, "dense_tropical", ModEntities.DEINONYCHUS.get(), 80, 2, 6);
        addToMap(map, "dense_tropical", ModEntities.DILOPHOSAURUS.get(), 80, 2, 5);
        addToMap(map, "dense_tropical", ModEntities.DODO.get(), 100, 3, 8);
        addToMap(map, "dense_tropical", ModEntities.EDAPHOSAURUS.get(), 60, 2, 5);
        addToMap(map, "dense_tropical", ModEntities.GALLIMIMUS.get(), 80, 2, 5);
        addToMap(map, "dense_tropical", ModEntities.MEGANEURA.get(), 100, 3, 8);
        addToMap(map, "dense_tropical", ModEntities.ORNITHOLESTES.get(), 80, 2, 5);
        addToMap(map, "dense_tropical", ModEntities.PACHYCEPHALOSAURUS.get(), 80, 2, 4);
        addToMap(map, "dense_tropical", ModEntities.PSITTACOSAURUS.get(), 80, 2, 6);
        addToMap(map, "dense_tropical", ModEntities.SPINOSAURUS.get(), 50, 1, 2);
        addToMap(map, "dense_tropical", ModEntities.THERIZINOSAURUS.get(), 60, 1, 3);

        // Ocean
        addToMap(map, "ocean", ModEntities.ICHTHYOSAURUS.get(), 40, 1, 8);
        addToMap(map, "ocean", ModEntities.MEGALODON.get(), 5, 1, 2);
        addToMap(map, "ocean", ModEntities.MOSASAURUS.get(), 5, 1, 2);

        // Deep Ocean
        addToMap(map, "deep_ocean", ModEntities.DICRANURUS.get(), 60, 1, 6);
        addToMap(map, "deep_ocean", ModEntities.ICHTHYOSAURUS.get(), 60, 1, 8);
        addToMap(map, "deep_ocean", ModEntities.LIOPLEURODON.get(), 20, 1, 4);
        addToMap(map, "deep_ocean", ModEntities.LONCHODOMAS.get(), 60, 1, 6);
        addToMap(map, "deep_ocean", ModEntities.MEGALODON.get(), 20, 1, 2);
        addToMap(map, "deep_ocean", ModEntities.PLESIOSAURUS.get(), 5, 1, 4);
        addToMap(map, "deep_ocean", ModEntities.SCOTOHARPES.get(), 60, 1, 6);
        addToMap(map, "deep_ocean", ModEntities.WALLISEROPS.get(), 60, 1, 6);

        // Cold Ocean
        addToMap(map, "cold_ocean", ModEntities.ICHTHYOSAURUS.get(), 40, 1, 8);
        addToMap(map, "cold_ocean", ModEntities.MOSASAURUS.get(), 5, 1, 2);

        // Deep Cold Ocean
        addToMap(map, "deep_cold_ocean", ModEntities.DICRANURUS.get(), 60, 1, 6);
        addToMap(map, "deep_cold_ocean", ModEntities.ICHTHYOSAURUS.get(), 60, 1, 8);
        addToMap(map, "deep_cold_ocean", ModEntities.LIOPLEURODON.get(), 20, 1, 4);
        addToMap(map, "deep_cold_ocean", ModEntities.LONCHODOMAS.get(), 60, 1, 6);
        addToMap(map, "deep_cold_ocean", ModEntities.PLESIOSAURUS.get(), 5, 1, 4);
        addToMap(map, "deep_cold_ocean", ModEntities.SCOTOHARPES.get(), 60, 1, 6);
        addToMap(map, "deep_cold_ocean", ModEntities.WALLISEROPS.get(), 60, 1, 6);

        // Warm Ocean
        addToMap(map, "warm_ocean", ModEntities.AQUILOLAMNA.get(), 40, 1, 4);
        addToMap(map, "warm_ocean", ModEntities.ICHTHYOSAURUS.get(), 40, 1, 8);
        addToMap(map, "warm_ocean", ModEntities.MEGALODON.get(), 5, 1, 2);
        addToMap(map, "warm_ocean", ModEntities.MOSASAURUS.get(), 5, 1, 2);

        // Deep Warm Ocean
        addToMap(map, "deep_warm_ocean", ModEntities.AQUILOLAMNA.get(), 40, 1, 4);
        addToMap(map, "deep_warm_ocean", ModEntities.DICRANURUS.get(), 60, 1, 6);
        addToMap(map, "deep_warm_ocean", ModEntities.ICHTHYOSAURUS.get(), 60, 1, 8);
        addToMap(map, "deep_warm_ocean", ModEntities.LIOPLEURODON.get(), 20, 1, 10);
        addToMap(map, "deep_warm_ocean", ModEntities.LONCHODOMAS.get(), 60, 1, 6);
        addToMap(map, "deep_warm_ocean", ModEntities.MEGALODON.get(), 20, 1, 2);
        addToMap(map, "deep_warm_ocean", ModEntities.SCOTOHARPES.get(), 60, 1, 6);
        addToMap(map, "deep_warm_ocean", ModEntities.WALLISEROPS.get(), 60, 1, 6);

        // Frozen Ocean
        addToMap(map, "frozen_ocean", ModEntities.ICHTHYOSAURUS.get(), 40, 1, 8);

        // Deep Frozen Ocean
        addToMap(map, "deep_frozen_ocean", ModEntities.ICHTHYOSAURUS.get(), 40, 1, 8);
        addToMap(map, "deep_frozen_ocean", ModEntities.PLESIOSAURUS.get(), 5, 1, 4);

        // River
        addToMap(map, "river", ModEntities.CERATOSAURUS.get(), 60, 1, 3);
        addToMap(map, "river", ModEntities.CONFUCIUSORNIS.get(), 80, 2, 6);
        addToMap(map, "river", ModEntities.DIMETRODON.get(), 80, 2, 5);
        addToMap(map, "river", ModEntities.DIMORPHODON.get(), 80, 2, 6);
        addToMap(map, "river", ModEntities.DIPLOCAULUS.get(), 100, 1, 4);
        addToMap(map, "river", ModEntities.DRYOSAURUS.get(), 80, 2, 5);
        addToMap(map, "river", ModEntities.EDAPHOSAURUS.get(), 80, 2, 5);
        addToMap(map, "river", ModEntities.GALLIMIMUS.get(), 60, 2, 5);
        addToMap(map, "river", ModEntities.HENODUS.get(), 80, 1, 3);
        addToMap(map, "river", ModEntities.MEGANEURA.get(), 100, 3, 8);
        addToMap(map, "river", ModEntities.PACHYCEPHALOSAURUS.get(), 80, 2, 4);
        addToMap(map, "river", ModEntities.PACHYRHINOSAURUS.get(), 80, 2, 5);
        addToMap(map, "river", ModEntities.PARASAUROLOPHUS.get(), 80, 2, 5);
        addToMap(map, "river", ModEntities.POSTOSUCHUS.get(), 60, 1, 2);
        addToMap(map, "river", ModEntities.QUETZALCOATLUS.get(), 40, 1, 3);
        addToMap(map, "river", ModEntities.SARCOSUCHUS.get(), 80, 1, 4);
        addToMap(map, "river", ModEntities.SPINOSAURUS.get(), 60, 1, 2);
        addToMap(map, "river", ModEntities.TIKTAALIK.get(), 80, 1, 4);

        return map;
    }
    
    private static void addToMap(Map<String, List<SpawnerData>> map, String category, EntityType<?> type, int weight, int min, int max) {
        map.computeIfAbsent(category, k -> new LinkedList<>()).add(new SpawnerData(type, weight, min, max));
    }

    public static class SpawnerData {
        public final EntityType<?> type;
        public final int weight;
        public final int minCount;
        public final int maxCount;

        public SpawnerData(EntityType<?> type, int weight, int minCount, int maxCount) {
            this.type = type;
            this.weight = weight;
            this.minCount = minCount;
            this.maxCount = maxCount;
        }
    }
    
    // --- Biome Helpers ---

    public static ResourceLocation[] getBadlandsBiomes() { return new ResourceLocation[] { Biomes.BADLANDS.location(), Biomes.ERODED_BADLANDS.location() }; }
    public static ResourceLocation[] getWoodedBadlandsBiomes() { return new ResourceLocation[] { Biomes.WOODED_BADLANDS.location() }; }
    public static ResourceLocation[] getBeachBiomes() { return new ResourceLocation[] { Biomes.BEACH.location() }; }
    public static ResourceLocation[] getRockyBeachBiomes() { return new ResourceLocation[] { Biomes.STONY_SHORE.location() }; }
    public static ResourceLocation[] getDesertBiomes() { return new ResourceLocation[] { Biomes.DESERT.location() }; }
    public static ResourceLocation[] getRedDesertBiomes() { return new ResourceLocation[] { Biomes.BADLANDS.location(), Biomes.ERODED_BADLANDS.location(), Biomes.DESERT.location() }; }
    public static ResourceLocation[] getForestBiomes() { return new ResourceLocation[] { Biomes.FOREST.location(), Biomes.FLOWER_FOREST.location() }; }
    public static ResourceLocation[] getAutumnalForestBiomes() { return new ResourceLocation[] { Biomes.BIRCH_FOREST.location(), Biomes.OLD_GROWTH_BIRCH_FOREST.location() }; }
    public static ResourceLocation[] getBirchForestBiomes() { return new ResourceLocation[] { Biomes.BIRCH_FOREST.location(), Biomes.OLD_GROWTH_BIRCH_FOREST.location() }; }
    public static ResourceLocation[] getCherryForestBiomes() { return new ResourceLocation[] { Biomes.CHERRY_GROVE.location() }; }
    public static ResourceLocation[] getDarkForestBiomes() { return new ResourceLocation[] { Biomes.DARK_FOREST.location() }; }
    public static ResourceLocation[] getSparseForestBiomes() { return new ResourceLocation[] { Biomes.WINDSWEPT_FOREST.location(), Biomes.WINDSWEPT_HILLS.location() }; }
    public static ResourceLocation[] getFrozenBiomes() { return new ResourceLocation[] { Biomes.SNOWY_PLAINS.location(), Biomes.ICE_SPIKES.location() }; }
    public static ResourceLocation[] getTundraBiomes() { return new ResourceLocation[] { Biomes.SNOWY_PLAINS.location(), Biomes.ICE_SPIKES.location(), Biomes.SNOWY_TAIGA.location() }; }
    public static ResourceLocation[] getMountainBiomes() { return new ResourceLocation[] { Biomes.STONY_PEAKS.location(), Biomes.WINDSWEPT_GRAVELLY_HILLS.location() }; }
    public static ResourceLocation[] getSnowyMountainBiomes() { return new ResourceLocation[] { Biomes.FROZEN_PEAKS.location(), Biomes.JAGGED_PEAKS.location(), Biomes.SNOWY_SLOPES.location() }; }
    public static ResourceLocation[] getPlainsBiomes() { return new ResourceLocation[] { Biomes.PLAINS.location(), Biomes.SUNFLOWER_PLAINS.location() }; }
    public static ResourceLocation[] getFloralFieldsBiomes() { return new ResourceLocation[] { Biomes.FLOWER_FOREST.location(), Biomes.MEADOW.location() }; }
    public static ResourceLocation[] getHighlandsBiomes() { return new ResourceLocation[] { Biomes.MEADOW.location(), Biomes.WINDSWEPT_HILLS.location() }; }
    public static ResourceLocation[] getPrairieBiomes() { return new ResourceLocation[] { Biomes.PLAINS.location(), Biomes.SUNFLOWER_PLAINS.location(), Biomes.MEADOW.location() }; }
    public static ResourceLocation[] getSavannaBiomes() { return new ResourceLocation[] { Biomes.SAVANNA.location(), Biomes.SAVANNA_PLATEAU.location(), Biomes.WINDSWEPT_SAVANNA.location() }; }
    public static ResourceLocation[] getSparseSavannaBiomes() { return new ResourceLocation[] { Biomes.SAVANNA.location() }; }
    public static ResourceLocation[] getDenseSavannaBiomes() { return new ResourceLocation[] { Biomes.SAVANNA_PLATEAU.location(), Biomes.WINDSWEPT_SAVANNA.location() }; }
    public static ResourceLocation[] getSeasonalBiomes() { return new ResourceLocation[] { Biomes.PLAINS.location(), Biomes.SUNFLOWER_PLAINS.location(), Biomes.MEADOW.location(), Biomes.SAVANNA.location(), Biomes.SAVANNA_PLATEAU.location() }; }
    public static ResourceLocation[] getDenseSeasonalBiomes() { return new ResourceLocation[] { Biomes.JUNGLE.location(), Biomes.SPARSE_JUNGLE.location() }; }
    public static ResourceLocation[] getDenseSemiAridBiomes() { return new ResourceLocation[] { Biomes.BADLANDS.location(), Biomes.WOODED_BADLANDS.location() }; }
    public static ResourceLocation[] getSparseSemiAridBiomes() { return new ResourceLocation[] { Biomes.SAVANNA.location(), Biomes.WINDSWEPT_SAVANNA.location(), Biomes.DESERT.location() }; }
    public static ResourceLocation[] getSwampBiomes() { return new ResourceLocation[] { Biomes.SWAMP.location() }; }
    public static ResourceLocation[] getMarshBiomes() { return new ResourceLocation[] { Biomes.MANGROVE_SWAMP.location(), Biomes.SWAMP.location() }; }
    public static ResourceLocation[] getWaterloggedSwampBiomes() { return new ResourceLocation[] { Biomes.MANGROVE_SWAMP.location() }; }
    public static ResourceLocation[] getTaigaBiomes() { return new ResourceLocation[] { Biomes.TAIGA.location() }; }
    public static ResourceLocation[] getMegaTaigaBiomes() { return new ResourceLocation[] { Biomes.OLD_GROWTH_PINE_TAIGA.location(), Biomes.OLD_GROWTH_SPRUCE_TAIGA.location() }; }
    public static ResourceLocation[] getSnowyTaigaBiomes() { return new ResourceLocation[] { Biomes.GROVE.location(), Biomes.SNOWY_TAIGA.location() }; }
    public static ResourceLocation[] getSparseTaigaBiomes() { return new ResourceLocation[] { Biomes.WINDSWEPT_FOREST.location() }; }
    public static ResourceLocation[] getRedwoodBiomes() { return new ResourceLocation[] { Biomes.OLD_GROWTH_PINE_TAIGA.location(), Biomes.OLD_GROWTH_SPRUCE_TAIGA.location() }; }
    public static ResourceLocation[] getTropicalBiomes() { return new ResourceLocation[] { Biomes.JUNGLE.location() }; }
    public static ResourceLocation[] getDenseTropicalBiomes() { return new ResourceLocation[] { Biomes.BAMBOO_JUNGLE.location() }; }
    public static ResourceLocation[] getSparseTropicalBiomes() { return new ResourceLocation[] { Biomes.SPARSE_JUNGLE.location() }; }
    public static ResourceLocation[] getOceanBiomes() { return new ResourceLocation[] { Biomes.OCEAN.location() }; }
    public static ResourceLocation[] getDeepOceanBiomes() { return new ResourceLocation[] { Biomes.DEEP_OCEAN.location() }; }
    public static ResourceLocation[] getColdOceanBiomes() { return new ResourceLocation[] { Biomes.COLD_OCEAN.location() }; }
    public static ResourceLocation[] getDeepColdOceanBiomes() { return new ResourceLocation[] { Biomes.DEEP_COLD_OCEAN.location() }; }
    public static ResourceLocation[] getWarmOceanBiomes() { return new ResourceLocation[] { Biomes.LUKEWARM_OCEAN.location(), Biomes.WARM_OCEAN.location() }; }
    public static ResourceLocation[] getDeepWarmOceanBiomes() { return new ResourceLocation[] { Biomes.DEEP_LUKEWARM_OCEAN.location() }; }
    public static ResourceLocation[] getFrozenOceanBiomes() { return new ResourceLocation[] { Biomes.FROZEN_OCEAN.location(), Biomes.SNOWY_BEACH.location() }; }
    public static ResourceLocation[] getDeepFrozenOceanBiomes() { return new ResourceLocation[] { Biomes.DEEP_FROZEN_OCEAN.location() }; }
    public static ResourceLocation[] getRiverBiomes() { return new ResourceLocation[] { Biomes.RIVER.location() }; }
}
