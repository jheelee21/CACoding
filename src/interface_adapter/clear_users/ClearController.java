package interface_adapter.clear_users;

import use_case.clear_users.ClearInputBoundary;
import use_case.clear_users.ClearInputData;

public class ClearController {

    final ClearInputBoundary userClearInputInteractor;

    public ClearController(ClearInputBoundary userClearInputInteractor) {
        this.userClearInputInteractor = userClearInputInteractor;
    }

    public void execute() {
        ClearInputData clearInputData = new ClearInputData();

        userClearInputInteractor.execute(clearInputData);
    }
}
