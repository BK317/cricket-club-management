import { createSlice } from '@reduxjs/toolkit';

// Function to load user from local storage
const loadUserFromLocalStorage = () => {
  try {
    const serializedState = localStorage.getItem('user');
    return serializedState ? JSON.parse(serializedState) : null;
  } catch (e) {
    console.error('Could not load user from local storage', e);
    return null;
  }
};

// Function to save user to local storage
const saveUserToLocalStorage = (user) => {
  try {
    const serializedState = JSON.stringify(user);
    localStorage.setItem('user', serializedState);
  } catch (e) {
    console.error('Could not save user to local storage', e);
  }
};

const initialState = {
  user: loadUserFromLocalStorage(),
};

const userSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    login: (state, action) => {
      state.user = action.payload;
      saveUserToLocalStorage(action.payload);
    },
    logout: (state) => {
      state.user = null;
      localStorage.removeItem('user');
    },
  },
});

export const { login, logout } = userSlice.actions;

export default userSlice.reducer;
