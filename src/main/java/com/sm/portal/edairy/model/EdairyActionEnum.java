package com.sm.portal.edairy.model;

public enum EdairyActionEnum {

	TODAYS_PAGE(1,"TODAYS_PAGE"),
	LAST_UPDATD_DATE(2,"LAST_UPDATD_DATE"),
	SELECTED_DATE(3,"SELECTED_DATE"),
	FAVORITE_PAGE(4,"FAVORITE_PAGE"),
	TITLE_PAGE(5,"TITLE_PAGE"),
	EDIT_PAGE(6,"EDIT_PAGE"),
	VIEW_PAGE(7,"VIEW_PAGE");
	
	private int	actionId;
	private String actionName;
	private EdairyActionEnum(int actionId, String actionName)
	{
		this.actionId = actionId;
		this.actionName = actionName;
	}
	public int getActionId() {
		return actionId;
	}
	public void setActionId(int actionId) {
		this.actionId = actionId;
	}
	public String getActioniStatus() {
		return actionName;
	}
	public void setActioinStatus(String actionName) {
		this.actionName = actionName;
	}
	
	static EdairyActionEnum[] values = values();

	public static EdairyActionEnum getStatus(int actionId)
	{
		for (EdairyActionEnum value : values)
		{
			if (value.getActionId() == actionId)
				return value;
		}
		return null;
	}
}
