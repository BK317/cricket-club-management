// src/services/teamsService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/teams';



const token =  "Bearer "+sessionStorage.getItem('token')

const body={
  headers: {
    token,
  },
}

export const getTeams = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    console.error("Error fetching teams", error);
    throw error;
  }
  
};


export const getTeamById = async (id) => {
    try {
        const response = await axios.get(`${API_URL}/${id}`);
        return response.data;
    } catch (error) {
        throw new Error('Error fetching team: ' + error.message);
    }
};

export const createTeam = async (teamData) => {

  const token =  "Bearer "+sessionStorage.getItem('token')
    try {
        const response = await axios.post(API_URL, teamData,{
            headers: {
              Authorization:token,
            },
          });
        return response.data;
    } catch (error) {
        throw new Error('Error creating team: ' + error.message);
    }
};

export const updateTeam = async (id, teamData) => {

   
const token =  "Bearer "+sessionStorage.getItem('token')
    try {
        const response = await axios.put(`${API_URL}/${id}`, teamData,{
            headers: {
             Authorization:token,
            },
          });
        return response.data;
    } catch (error) {
        throw new Error('Error updating team: ' + error.message);
    }
};

export const deleteTeam = async (id) => {

  const token =  "Bearer "+sessionStorage.getItem('token')
    try {
        await axios.delete(`${API_URL}/${id}`,{
            headers: {
              Authorization:token,
            },
          });
    } catch (error) {
        throw new Error('Error deleting team: ' + error.message);
    }
};