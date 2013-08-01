package models.build;

import java.util.Date;

import models.AbstractData;

public class BuildData extends AbstractData {
	public int run_id;
	public String build_type;
	public String build_number;
	public String commit_id;
	public String build_timestamp;
	public int regression_status;
	public String repository;
	public int deploy_to_prodn;
	public int deploy_to_staging;
	public int deploy_to_preprod;
	
	public int getRun_id() {
		return run_id;
	}
	public void setRun_id(int run_id) {
		this.run_id = run_id;
	}
	public String getBuild_type() {
		return build_type;
	}
	public void setBuild_type(String build_type) {
		this.build_type = build_type;
	}
	public String getBuild_number() {
		return build_number;
	}
	public void setBuild_number(String build_number) {
		this.build_number = build_number;
	}
	public String getCommit_id() {
		return commit_id;
	}
	public void setCommit_id(String commit_id) {
		this.commit_id = commit_id;
	}
	public String getBuild_timestamp() {
		return build_timestamp;
	}
	public void setBuild_timestamp(String build_timestamp) {
		this.build_timestamp = build_timestamp;
	}
	public int getRegression_status() {
		return regression_status;
	}
	public void setRegression_status(int regression_status) {
		this.regression_status = regression_status;
	}
	public String getRepository() {
		return repository;
	}
	public void setRepository(String repository) {
		this.repository = repository;
	}
	public int getDeploy_to_prodn() {
		return deploy_to_prodn;
	}
	public void setDeploy_to_prodn(int deploy_to_prodn) {
		this.deploy_to_prodn = deploy_to_prodn;
	}
	public int getDeploy_to_staging() {
		return deploy_to_staging;
	}
	public void setDeploy_to_staging(int deploy_to_staging) {
		this.deploy_to_staging = deploy_to_staging;
	}
	public int getDeploy_to_preprod() {
		return deploy_to_preprod;
	}
	public void setDeploy_to_preprod(int deploy_to_preprod) {
		this.deploy_to_preprod = deploy_to_preprod;
	}
	
	
}
