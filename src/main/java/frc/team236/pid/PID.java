package frc.team236.pid;

import frc.team236.ticktock.Tickable;

public class PID implements Tickable {
	private volatile PIDParameters gains;
	private PIDSource source;
	private PIDOutput output;
	private volatile double setpoint;
	private volatile double error;
	private double errSum;
	private double dInput;

	private double lastInput;

	private double total;

	/**
	 * An updatable that handles PID control of a system
	 * 
	 * @author Sam
	 * @param _source
	 *            The object from which to read the position of the system
	 * @param _output
	 *            The object to set speed with PID control
	 * @param _gains
	 *            The PID gains to configurate the PID controller
	 */
	public PID(PIDSource _source, PIDOutput _output, PIDParameters _gains) {
		this.setGains(_gains);
		this.source = _source;
		this.output = _output;
	}

	public synchronized void setGains(PIDParameters _gains) {
		double kP = _gains.kP;
		double kI = _gains.kI * _gains.interval;
		double kD = _gains.kD / _gains.interval;
		this.gains = new PIDParameters(kP, kI, kD, _gains.interval);
	}

	public void setSetpoint(double _setpoint) {
		this.setpoint = _setpoint;
	}

	@Override
	public void update() {
		double input = this.source.getPos();

		// Compute working error vars
		error = setpoint - source.getPos();
		errSum += error;
		dInput = input - lastInput;

		// Compute PID output
		total = (gains.kP * error) + (gains.kI * errSum) - (gains.kD * dInput);

		// Set output to the computed value
		output.setSpeed(total);

		// Get ready for next update
		lastInput = input;
	}
}
