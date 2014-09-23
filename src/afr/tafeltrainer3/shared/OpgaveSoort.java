package afr.tafeltrainer3.shared;

import com.google.gwt.user.client.ui.CheckBox;

public class OpgaveSoort {

private CheckBox[] boxes;
private boolean x2;
private boolean x3;
private boolean x4;
private boolean x5;
private boolean x6;
private boolean x7;
private boolean x8;
private boolean x9;
private boolean xalle;
private boolean xmoeilijk;

	public OpgaveSoort(CheckBox[] boxes)
		{
			this.boxes = boxes;
			this.x2 = 		boxes[0].getValue();
			this.x3 = 		boxes[1].getValue();
			this.x4 = 		boxes[2].getValue();
			this.x5 = 		boxes[3].getValue();
			this.x6 = 		boxes[4].getValue();
			this.x7 = 		boxes[5].getValue();
			this.x8 = 		boxes[6].getValue();
			this.x9 = 		boxes[7].getValue();
			this.xalle = 	boxes[8].getValue();
			this.xmoeilijk =	boxes[9].getValue();
			
		}

	public CheckBox[] getBoxes() {
		return boxes;
	}

	public void setBoxes(CheckBox[] boxes) {
		this.boxes = boxes;
	}

	public boolean isX2() {
		return x2;
	}

	public void setX2(boolean x2) {
		this.x2 = x2;
	}

	public boolean isX3() {
		return x3;
	}

	public void setX3(boolean x3) {
		this.x3 = x3;
	}

	public boolean isX4() {
		return x4;
	}

	public void setX4(boolean x4) {
		this.x4 = x4;
	}

	public boolean isX5() {
		return x5;
	}

	public void setX5(boolean x5) {
		this.x5 = x5;
	}

	public boolean isX6() {
		return x6;
	}

	public void setX6(boolean x6) {
		this.x6 = x6;
	}

	public boolean isX7() {
		return x7;
	}

	public void setX7(boolean x7) {
		this.x7 = x7;
	}

	public boolean isX8() {
		return x8;
	}

	public void setX8(boolean x8) {
		this.x8 = x8;
	}

	public boolean isX9() {
		return x9;
	}

	public void setX9(boolean x9) {
		this.x9 = x9;
	}

	public boolean isXalle() {
		return xalle;
	}

	public void setX10(boolean x10) {
		this.xalle = x10;
	}

	public boolean isX11() {
		return xmoeilijk;
	}

	public void setX11(boolean x11) {
		this.xmoeilijk = x11;
	}


	
}
