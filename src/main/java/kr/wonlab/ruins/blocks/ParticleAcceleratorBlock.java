package kr.wonlab.ruins.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ParticleAcceleratorBlock extends Block {

    public static final BooleanProperty POWERED = BooleanProperty.of("powered");

    public ParticleAcceleratorBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(POWERED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(POWERED);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(player.inventory.getMainHandStack() == null)
            return ActionResult.PASS;
        world.setBlockState(pos, state.with(POWERED, true));
        return ActionResult.SUCCESS;
    }

}
