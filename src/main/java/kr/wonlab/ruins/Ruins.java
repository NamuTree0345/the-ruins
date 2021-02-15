package kr.wonlab.ruins;

import kr.wonlab.ruins.blocks.ParticleAcceleratorBlock;
import kr.wonlab.ruins.fluids.PollutedWaterFluid;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import net.minecraft.block.*;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.client.render.SkyProperties;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Ruins implements ModInitializer {

    public static ItemGroup RUINS_ITEMGROUP = FabricItemGroupBuilder.build(
            new Identifier("ruins", "ruins_itemgroup"),
            () -> new ItemStack(Blocks.END_PORTAL_FRAME)
    );;

    public static Block POLLUTED_WATER;
    public static FlowableFluid STILL_POLLUTED_WATER;
    public static FlowableFluid FLOWING_POLLUTED_WATER;
    public static Item POLLUTED_WATER_BUCKET;

    public static ConfiguredFeature<TreeFeatureConfig, ?> POLLUTED_TREE;

    public static Block POLLUTED_TREE_LOG;
    public static Item POLLUTED_TREE_LOG_ITEM;
    public static Block POLLUTED_TREE_LOG_SAPLING;
    public static Item POLLUTED_TREE_LOG_SAPLING_ITEM;

    public static Block PARTICLE_ACCELERATOR = new ParticleAcceleratorBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    public static Item PARTICLE_ACCELERATOR_ITEM;

    public static Block FLAME_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f).requiresTool().resistance(3.0f));
    public static Block ICE_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f).requiresTool().resistance(3.0f));

    public static Item FLAME_ORE_ITEM = new BlockItem(FLAME_ORE, new FabricItemSettings().group(RUINS_ITEMGROUP));
    public static Item ICE_ORE_ITEM = new BlockItem(ICE_ORE, new FabricItemSettings().group(RUINS_ITEMGROUP));

    public static ConfiguredFeature<?, ?> FLAME_ORE_FEATURE;
    public static ConfiguredFeature<?, ?> ICE_ORE_FEATURE;

    @Override
    public void onInitialize() {

        STILL_POLLUTED_WATER = Registry.register(Registry.FLUID, new Identifier("ruins", "polluted_water"), new PollutedWaterFluid.Still());
        FLOWING_POLLUTED_WATER = Registry.register(Registry.FLUID, new Identifier("ruins", "flowing_polluted_water"), new PollutedWaterFluid.Flowing());
        POLLUTED_WATER_BUCKET = Registry.register(Registry.ITEM, new Identifier("ruins", "polluted_water_bucket"), new BucketItem(STILL_POLLUTED_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1).group(RUINS_ITEMGROUP)));
        POLLUTED_WATER = Registry.register(Registry.BLOCK, new Identifier("ruins", "polluted_water"), new FluidBlock(STILL_POLLUTED_WATER, FabricBlockSettings.copy(Blocks.WATER)){});

        PARTICLE_ACCELERATOR = Registry.register(Registry.BLOCK, new Identifier("ruins", "particle_accelerator"), PARTICLE_ACCELERATOR);
        PARTICLE_ACCELERATOR_ITEM = Registry.register(Registry.ITEM, new Identifier("ruins", "particle_accelerator"), new BlockItem(PARTICLE_ACCELERATOR, new FabricItemSettings().group(RUINS_ITEMGROUP)));

        POLLUTED_TREE_LOG = Registry.register(Registry.BLOCK, new Identifier("ruins", "polluted_tree_log"), createLogBlock(MaterialColor.WOOD, MaterialColor.SPRUCE));
        POLLUTED_TREE_LOG_ITEM = Registry.register(Registry.ITEM, new Identifier("ruins", "polluted_tree_log"), new BlockItem(POLLUTED_TREE_LOG, new FabricItemSettings().group(RUINS_ITEMGROUP)));
        try {
            Constructor<SaplingBlock> constructor = SaplingBlock.class.getConstructor(SaplingGenerator.class, AbstractBlock.Settings.class);
            constructor.setAccessible(true);
            SaplingBlock sb = constructor.newInstance(new PollutedTreeSaplingGenerator(), AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
            POLLUTED_TREE_LOG_SAPLING = Registry.register(Registry.BLOCK, new Identifier("ruins", "polluted_tree_sapling"), sb);
            POLLUTED_TREE_LOG_SAPLING_ITEM = Registry.register(Registry.ITEM, new Identifier("ruins", "polluted_tree_sapling"), new BlockItem(POLLUTED_TREE_LOG_SAPLING, new FabricItemSettings().group(RUINS_ITEMGROUP)));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // OAK = register("oak", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(ConfiguredFeatures.States.OAK_LOG), new SimpleBlockStateProvider(ConfiguredFeatures.States.OAK_LEAVES), new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));
        POLLUTED_TREE = registerConfiguredFeature("polluted_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(POLLUTED_TREE_LOG.getDefaultState()), new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()), new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

        FLAME_ORE = Registry.register(Registry.BLOCK, new Identifier("ruins", "flame_ore"), FLAME_ORE);
        FLAME_ORE_ITEM = Registry.register(Registry.ITEM, new Identifier("ruins", "flame_ore"), FLAME_ORE_ITEM);
        ICE_ORE = Registry.register(Registry.BLOCK, new Identifier("ruins", "ice_ore"), ICE_ORE);
        ICE_ORE_ITEM = Registry.register(Registry.ITEM, new Identifier("ruins", "ice_ore"), ICE_ORE_ITEM);

        FLAME_ORE_FEATURE = Feature.ORE
                .configure(new OreFeatureConfig(
                        OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                        FLAME_ORE.getDefaultState(),
                        9)) // vein size
                .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
                        0,
                        0,
                        158)))
                .spreadHorizontally()
                .repeat(20);
        RegistryKey<ConfiguredFeature<?, ?>> flameOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier("ruins", "flame_ore"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, flameOre.getValue(), FLAME_ORE_FEATURE);
        ICE_ORE_FEATURE = Feature.ORE
                .configure(new OreFeatureConfig(
                        OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                        ICE_ORE.getDefaultState(),
                        9)) // vein size
                .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
                        0,
                        0,
                        64)))
                .spreadHorizontally()
                .repeat(20);
        RegistryKey<ConfiguredFeature<?, ?>> iceOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier("ruins", "ice_ore"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, iceOre.getValue(), ICE_ORE_FEATURE);

    }

    private static PillarBlock createLogBlock(MaterialColor topMaterialColor, MaterialColor sideMaterialColor) {
        return new PillarBlock(FabricBlockSettings.of(Material.WOOD, (blockState) -> {
            return blockState.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMaterialColor : sideMaterialColor;
        }).strength(2.0F).sounds(BlockSoundGroup.WOOD));
    }

    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> registerConfiguredFeature(String id, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("ruins", id), configuredFeature);
    }
}
