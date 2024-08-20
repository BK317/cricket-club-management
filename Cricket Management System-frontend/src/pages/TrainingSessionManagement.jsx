import React, { useState, useEffect } from 'react';
import { Container, Table } from 'react-bootstrap';
import { getTrainingSessions } from '../services/trainingSessionService';

const TrainingSessionManagement = () => {
  const [trainingSessions, setTrainingSessions] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchTrainingSessions = async () => {
      try {
        const data = await getTrainingSessions();
        setTrainingSessions(data);
      } catch (error) {
        setError(error.message);
      }
    };
    fetchTrainingSessions();
  }, []);

  return (
    <Container>
      <h2>Manage Training Sessions</h2>
      {error && <div className="alert alert-danger">{error}</div>}
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>#</th>
            <th>Session Name</th>
            <th>Session Date</th>
          </tr>
        </thead>
        <tbody>
          {trainingSessions.map((session) => (
            <tr key={session.id}>
              <td>{session.id}</td>
              <td>{session.sessionName}</td>
              <td>{new Date(session.sessionDate).toLocaleString()}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
};

export default TrainingSessionManagement;