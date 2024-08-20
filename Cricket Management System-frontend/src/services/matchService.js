import axios from 'axios';

const BASE_URL = 'http://localhost:8080/matches'; // Adjust the base URL as needed




export const getMatches = async () => {
  try {
    const response = await axios.get(BASE_URL);
    return response.data;
  } catch (error) {
    console.error('Error fetching matches', error);
    throw error;
  }
};

export const getMatchById = async (id) => {
  try {
    const response = await axios.get(`${BASE_URL}/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching match with id ${id}`, error);
    throw error;
  }
};

export const createMatch = async (matchData) => {
  const token =  "Bearer "+sessionStorage.getItem('token')
  try {
    const response = await axios.post(BASE_URL, matchData,{
      headers: {
        Authorization:token,
      },
    }
    );
    return response.data;
  } catch (error) {
    console.error('Error creating match', error);
    throw error;
  }
};

export const updateMatch = async (id, matchData) => {
  const token =  "Bearer "+sessionStorage.getItem('token')
  try {
    const response = await axios.put(`${BASE_URL}/${id}`, matchData,{
      headers: {
        Authorization:token,
      },
    });
    return response.data;
  } catch (error) {
    console.error(`Error updating match with id ${id}`, error);
    throw error;
  }
};

export const deleteMatch = async (id) => {

  const token =  "Bearer "+sessionStorage.getItem('token')
  try {
    await axios.delete(`${BASE_URL}/${id}`,{
      headers: {
        Authorization:token,
      },
    });
  } catch (error) {
    console.error(`Error deleting match with id ${id}`, error);
    throw error;
  }
};
