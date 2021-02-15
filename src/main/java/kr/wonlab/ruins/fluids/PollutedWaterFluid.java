package kr.wonlab.ruins.fluids;

import kr.wonlab.ruins.Ruins;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class PollutedWaterFluid extends AbstractPollutedWaterFluid {

    @Override
    public Fluid getStill()
    {
        return Ruins.STILL_POLLUTED_WATER;
    }

    @Override
    public Fluid getFlowing()
    {
        return Ruins.FLOWING_POLLUTED_WATER;
    }

    @Override
    public Item getBucketItem()
    {
        return Ruins.POLLUTED_WATER_BUCKET;
    }

    @Override
    public boolean isStill(FluidState state) {
        return false;
    }

    @Override
    public int getLevel(FluidState state) {
        return 0;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState)
    {
        // method_15741 converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses

        return Ruins.POLLUTED_WATER.getDefaultState().with(Properties.LEVEL_15, method_15741(fluidState));
    }

    public static class Flowing extends PollutedWaterFluid
    {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder)
        {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState fluidState)
        {
            return fluidState.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState fluidState)
        {
            return false;
        }
    }

    public static class Still extends PollutedWaterFluid
    {
        @Override
        public int getLevel(FluidState fluidState)
        {
            return 8;
        }

        @Override
        public boolean isStill(FluidState fluidState)
        {
            return true;
        }
    }
}
