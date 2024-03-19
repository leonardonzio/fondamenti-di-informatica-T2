package minesweeper.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;
import java.util.zip.ZipEntry;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import org.junit.jupiter.migrationsupport.rules.member.TestRuleAnnotatedMethod;
import org.junit.runners.model.FrameworkField;

public class RealMineField extends MineField {

	private int mines;
	
	public RealMineField(int size, int minesCount) {
		super(size);
		this.mines = minesCount;
		init();
	}
	
	
	public RealMineField(int size) {
		this(size, 10);
	}
	
	@Override
	protected void init() {
		
		var r = new Random();
		var listRandRows = new ArrayList<Integer>();	
		var listRandCols = new ArrayList<Integer>();
		
		while (listRandRows.size() < getSize())
		{
			int randRow = r.nextInt(getSize() - 1);
			int randCol = r.nextInt(getSize() - 1);

			if (!listRandRows.add(randRow)) {
				var t = listRandCols.indexOf(randRow);
				if (listRandCols.get(t) == randCol) {
					continue;
				}
			}		
			
			listRandCols.add(randCol);
			listRandRows.add(randRow);
			setCell(randRow, randCol, new Cell(CellType.MINE));
		}
		
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (getCell(i, j).getType() == (CellType.HIDDEN))
					calcAdjentMines(i, j);
			}
		}
	}

	public int getMinesNumber() {
		return mines;
	}
	
	private void calcAdjentMines(int row, int col) {
		int startingRow 	= row == 0 				? 0 : -1;
		int finishingRow	= row == (getSize()-1) 	? 0 : +1;
		
		int startingCol		= col == 0 				? 0 : -1;
		int finishingCol	= col == (getSize()-1) 	? 0 : +1;

		int count = 0;
		
		for (int i = row + startingRow; i < row + finishingRow; i++) {
			for (int j = col + startingCol; j < col + finishingCol; j++) {
				
				if (i == row && j == col) continue;
				if (getCell(row, col) != null && getCell(row, col).getType() == CellType.MINE)
					count++;
				
			}
		}
		setCell(row, col, new Cell(CellType.NUM, count));
	}
	
	
	
}
