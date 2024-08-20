// src/services/PlayerService.js
import axios from 'axios';

// Base URL for your backend API
const API_URL = 'http://localhost:8080/players'; // Update with your actual backend URL

const token = sessionStorage.getItem('token')

const body={
  headers: {
    token,
  },
}

// Fetch all players
export const getPlayers = async () => {
    try {
        const response = await axios.get(API_URL);
        return response.data;
    } catch (error) {
        console.error('Error fetching players:', error);
        throw error;
    }
};

// Add a new player
export const addPlayer = async (playerData) => {
  const token =  "Bearer "+sessionStorage.getItem('token')
    try {
        const response = await axios.post(API_URL, playerData,{
            headers: {
              Authorization:token,
            },
          }
          );
        return response.data;
    } catch (error) {
        console.error('Error adding player:', error);
        throw error;
    }
};

// Update an existing player
export const updatePlayer = async (playerId, playerData) => {
  const token =  "Bearer "+sessionStorage.getItem('token')
    try {
        const response = await axios.put(`${API_URL}/${playerId}`, playerData,{
            headers: {
              Authorization:token,
            },
          }
          );
        return response.data;
    } catch (error) {
        console.error('Error updating player:', error);
        throw error;
    }
};

// Delete a player
export const deletePlayer = async (playerId) => {
  const token =  "Bearer "+sessionStorage.getItem('token')
    try {
        await axios.delete(`${API_URL}/${playerId}`,{
            headers: {
              Authorization:token,
            },
          }
          );
    } catch (error) {
        console.error('Error deleting player:', error);
        throw error;
    }
};

// Approve a player
// Approve a player
export const approvePlayer = async (playerId) => {
  const token = "Bearer " + sessionStorage.getItem('token');

  try {
    const response = await axios.put(
      `${API_URL}/${playerId}/approve`,
      {}, // Empty object for the request body since it's a PUT request without body
      {
        headers: {
          Authorization: token,
        },
      }
    );
    return response.data;
  } catch (error) {
    console.error('Error approving player:', error);
    throw error;
  }
};


// Reject a player
export const rejectPlayer = async (playerId) => {
  const token =  "Bearer "+sessionStorage.getItem('token')
    try {
        const response = await axios.put(`${API_URL}/${playerId}/reject`,{
            headers: {
              Authorization:token,
            },
          }
          );
        return response.data;
    } catch (error) {
        console.error('Error rejecting player:', error);
        throw error;
    }
};
