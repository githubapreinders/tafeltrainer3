package afr.tafeltrainer3.client;

import java.util.ArrayList;

import afr.tafeltrainer3.shared.Opgave;
import afr.tafeltrainer3.shared.OpgaveSoort;
import afr.tafeltrainer3.shared.ProductWidget;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Tafeltrainer3Gui_mobile extends Devicetype
{
	
	public Tafeltrainer3Gui_mobile()
	{
		super.txt1.addFocusHandler(new FocusHandler()
		{
			@Override
			public void onFocus(FocusEvent event)
			{
				txt1.getElement().blur();
			}
		});
		super.txt1.addValueChangeHandler(new ValueChangeHandler<String>()
		{
			
			@Override
			public void onValueChange(ValueChangeEvent<String> event)
			{
				answersubmitted();				
			}
		});
			
			
	}

	public void onNumPadUp()
	{
		int answer = opg.getAntwoord();
		int numofdigits = (int) Math.log10(answer) + 1;

		if (numofdigits == 1)
		{
			computeTime();
			tim.cancel();
			int antwoord = Integer.valueOf(txt1.getText());
			//txt1.setFocus(true);
			opg.setUseranswer(antwoord);
			if (opg.antwoord == antwoord)
				opg.setIscorrect(true);
			else
				opg.setIscorrect(false);
			updateLabel3(opg);
			client.submitQuestion(opg, main.getUser().getId());
			opg = new Opgave(opgavesoort);
			numpad.number = "";
			updateLabel2(opg);
		}

		if (numofdigits == 2)
		{
			computeTime();
			tim.cancel();
			int antwoord = Integer.valueOf(txt1.getText());
			//txt1.setFocus(true);
			opg.setUseranswer(antwoord);
			if (opg.antwoord == antwoord)
				opg.setIscorrect(true);
			else
				opg.setIscorrect(false);
			updateLabel3(opg);
			client.submitQuestion(opg, main.getUser().getId());
			opg = new Opgave(opgavesoort);
			updateLabel2(opg);
			numpad.number = "";
		}
		if (numofdigits == 3)
		{
			computeTime();
			tim.cancel();
			int antwoord = Integer.valueOf(txt1.getText());
			opg.setUseranswer(antwoord);
			//txt1.setFocus(true);
			if (opg.antwoord == antwoord)
				opg.setIscorrect(true);
			else
				opg.setIscorrect(false);
			updateLabel3(opg);
			client.submitQuestion(opg, main.getUser().getId());
			opg = new Opgave(opgavesoort);
			updateLabel2(opg);
			numpad.number = "";
		}
	}

	public void answersubmitted()
	{
		try
		{
			int answer = opg.getAntwoord();
			int numofdigits = (int) Math.log10(answer) + 1;

			if (numofdigits == 1)
			{
				computeTime();
				tim.cancel();
				int antwoord = Integer.valueOf(txt1.getText());
				txt1.setText("");
				txt1.setFocus(true);
				opg.setUseranswer(antwoord);
				if (opg.antwoord == antwoord)
					opg.setIscorrect(true);
				else
					opg.setIscorrect(false);
				updateLabel3(opg);
				client.submitQuestion(opg, main.getUser().getId());
				opg = new Opgave(opgavesoort);
				flag = 0;
				updateLabel2(opg);
			}

			if (numofdigits == 2)

				switch (flag)
				{
				case 1:
					computeTime();
					tim.cancel();
					int antwoord = Integer.valueOf(txt1.getText());
					txt1.setText("");
					txt1.setFocus(true);
					opg.setUseranswer(antwoord);
					if (opg.antwoord == antwoord)
						opg.setIscorrect(true);
					else
						opg.setIscorrect(false);
					updateLabel3(opg);
					client.submitQuestion(opg, main.getUser().getId());
					opg = new Opgave(opgavesoort);
					updateLabel2(opg);
					break;
				case 0:
					flag = 1;
					break;
				}
			if (numofdigits == 3)

				switch (secondflag)
				{
				case 0:
					secondflag = 1;
					break;
				case 1:
					secondflag = 2;
					break;
				case 2:
					computeTime();
					tim.cancel();
					int antwoord = Integer.valueOf(txt1.getText());
					txt1.setText("");
					txt1.setFocus(true);
					opg.setUseranswer(antwoord);
					if (opg.antwoord == antwoord)
						opg.setIscorrect(true);
					else
						opg.setIscorrect(false);
					updateLabel3(opg);
					secondflag = 0;
					client.submitQuestion(opg, main.getUser().getId());
					opg = new Opgave(opgavesoort);
					updateLabel2(opg);
					break;
				}
		} catch (NumberFormatException n)
		{
			txt1.setText("");
			txt1.setFocus(true);
		}

	}
	
}
