package frc.team236.pid;

import jaci.openrio.toast.lib.log.Logger;
import jaci.openrio.toast.lib.module.IterativeModule;

public class RobotModule extends IterativeModule {

	public static Logger logger;

	@Override
	public String getModuleName() {
		return "PID";
	}

	@Override
	public String getModuleVersion() {
		return "1.0.0";
	}

	@Override
	public void robotInit() {
		logger = new Logger("PID", Logger.ATTR_DEFAULT);
		//TODO: Module Init
	}
}
