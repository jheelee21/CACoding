package interface_adapter.clear_users;

import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import use_case.clear_users.ClearOutputBoundary;
import use_case.clear_users.ClearOutputData;

public class ClearPresenter implements ClearOutputBoundary{

    private final ClearViewModel clearViewModel;
    private final SignupViewModel signupViewModel;
    private final ViewManagerModel viewManagerModel;

    public ClearPresenter(ViewManagerModel viewManagerModel,
                          ClearViewModel clearViewModel,
                          SignupViewModel signupViewModel){
        this.viewManagerModel = viewManagerModel;
        this.clearViewModel = clearViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(ClearOutputData response) {
        ClearState clearState = clearViewModel.getState();
        clearState.setUsers(response.getUsers());

        clearViewModel.firePropertyChanged();
    }
}