package src.gamrcorps.particlesmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import src.gamrcorps.particlesmod.main.ItemRegistry;
import src.gamrcorps.particlesmod.main.ParticlesMod;
import src.gamrcorps.particlesmod.tileentity.TileEntityAdvancedHopper;
import src.gamrcorps.particlesmod.tileentity.TileEntitySolarCollector;
import src.gamrcorps.particlesmod.util.WorldUtils;

import java.util.Random;

/**
 * Created by matthewmccaskill on 6/10/16.
 */
public class BlockSolarCollector extends Block implements ITileEntityProvider{
    private String name = "solarCollector";
    protected static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
    private int energyLevel = 0;
    private int energy = 0;

    public BlockSolarCollector() {
        super(Material.REDSTONE_LIGHT, MapColor.LAPIS);
        this.setUnlocalizedName(this.name);
        this.setRegistryName(this.name);
        this.setCreativeTab(ParticlesMod.particleCreativeTab);
    }

    public String getName() {
        return this.name;
    }

    @Override
    @SuppressWarnings({"deprecation", "NullableProblems"})
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDING_BOX;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    /*public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.canBlockSeeSky(pos)) return;
        //super.updateTick();
        //0-1000:1
        //1000-2000:2
        //2000-3000:3
        //3000-4000:4
        //4000-5000:5
        //5000-7000:6
        //7000-8000:5
        //8000-9000:4
        //9000-10000:3
        //10000-11000:2
        //11000-12000:1
        double time = world.getWorldTime() / 1000.0;
        int level = (int) Math.ceil(time);
        switch (level){
            case 1:
                energyLevel = 1;
            case 2:
                energyLevel = 2;
            case 3:
                energyLevel = 3;
            case 4:
                energyLevel = 4;
            case 5:
                energyLevel = 5;
            case 6:
                energyLevel = 6;
            case 7:
                energyLevel = 6;
            case 8:
                energyLevel = 5;
            case 9:
                energyLevel = 4;
            case 10:
                energyLevel = 3;
            case 11:
                energyLevel = 2;
            case 12:
                energyLevel = 1;
        }
        System.out.println(energyLevel);
        energy += energyLevel;
        if (energy >= 3000) {
            energy = 0;
            WorldUtils.spawnItem(world, new ItemStack(ItemRegistry.getItem("particleLight")), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
        }
    }*/

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntitySolarCollector();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            playerIn.addChatComponentMessage(new TextComponentString("Energy: " + ((TileEntitySolarCollector) worldIn.getTileEntity(pos)).energy + "/2400\nEnergy Level: " + ((TileEntitySolarCollector) worldIn.getTileEntity(pos)).energyLevel + "/6"));
        }
        return true;
    }
}
