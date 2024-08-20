import axios from 'axios';

const BASE_URL = 'http://localhost:8080/grounds';

export const getGrounds = async () => {
  const response = await axios.get(BASE_URL);
  return response.data;
};

export const addGround = async (groundData) => {
  const response = await axios.post(BASE_URL, groundData);
  return response.data;
};

export const updateGround = async (id, groundData) => {
  const response = await axios.put(`${BASE_URL}/${id}`, groundData);
  return response.data;
};
