package guiSideB;

import org.eclipse.swt.widgets.Composite;

import ana_rules.Comparator_Operator;
import ana_rules.RuleObject;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.layout.GridData;

public class RuleObjectEdit extends Composite {
	private RuleObject metric;
	private RuleObject comparator;
	private RuleObject threshold;
	private GuiRegraEdit mainWindow;
	private Spinner thresholdlbl;

	/**
	 * Creates the composite
	 * 
	 * @param parent     the composite
	 * @param style      number
	 * @param mainWindow the main gui window
	 * @param metric
	 * @param comparator
	 * @param threshold
	 */
	public RuleObjectEdit(Composite parent, int style, GuiRegraEdit mainWindow, RuleObject metric,
			RuleObject comparator, RuleObject threshold) {
		super(parent, style);
		this.mainWindow = mainWindow;
		this.metric = metric;
		this.comparator = comparator;
		this.threshold = threshold;
		setLayout(new GridLayout(3, false));

		Label metriclbl = new Label(this, SWT.NONE);
		metriclbl.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		metriclbl.setText(metric.getInfo());

		Label comparatorlbl = new Label(this, SWT.NONE);
		comparatorlbl.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		comparatorlbl.setText(Comparator_Operator.valueOf(comparator.getInfo()).getString());

		thresholdlbl = new Spinner(this, SWT.BORDER);
		thresholdlbl.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		thresholdlbl.setSelection(Integer.parseInt(threshold.getInfo()));
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	/**
	 * Checks if the threshold value was edited
	 * 
	 * @return true if the threshold value was edited
	 */
	protected Boolean isEdited() {
		return thresholdlbl.getSelection() != Integer.parseInt(getThreshold().getInfo());
	}

	/**
	 * Gets the new object info
	 * 
	 * @return the new object info
	 */
	protected RuleObject newObjectInfo() {
		return new RuleObject(String.valueOf(thresholdlbl.getSelection()), threshold.getLabel().toString());
	}

	/**
	 * Gets the threshold
	 * 
	 * @return the threshold
	 */
	protected RuleObject getThreshold() {
		return threshold;
	}
}
