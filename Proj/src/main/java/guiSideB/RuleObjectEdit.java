package guiSideB;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Spinner;

import rules.Comparator_Operator;
import rules.RuleObject;

import org.eclipse.swt.layout.GridData;

public class RuleObjectEdit extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	private RuleObject metric;
	private RuleObject comparator;
	private RuleObject threshold;
	private GuiRegraEdit mainWindow;
	private Spinner thresholdlbl;
	
	public RuleObjectEdit(Composite parent, int style,GuiRegraEdit mainWindow,RuleObject metric,RuleObject comparator,RuleObject threshold) {
		super(parent, style);
		this.mainWindow=mainWindow;
		this.metric=metric;
		this.comparator=comparator;
		this.threshold=threshold;
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
	
	protected Boolean isEdited() {
		return thresholdlbl.getSelection()!=Integer.parseInt(getThreshold().getInfo());
	}
	
	protected RuleObject newObjectInfo() {
		return new RuleObject(String.valueOf(thresholdlbl.getSelection()),threshold.getLabel().toString());
	}

	protected RuleObject getThreshold() {
		return threshold;
	}
}
