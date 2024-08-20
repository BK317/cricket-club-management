import axios from 'axios';

const API_URL = 'http://localhost:8080/players/training-sessions'; // Update with your actual API URL

export const getTrainingSessions = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    throw new Error('Error fetching training sessions: ' + error.message);
  }
};