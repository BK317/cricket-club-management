import React, { useState, useEffect } from 'react';
import { Container, Table, Button, Modal, Form, Alert } from 'react-bootstrap';
import { getMatches, createMatch, updateMatch, deleteMatch } from '../services/matchService';
import AdminLayout from '../components/AdminLayout';

const MatchManagement = () => {
  const [matches, setMatches] = useState([]);
  const [show, setShow] = useState(false);
  const [editingMatch, setEditingMatch] = useState(null);
  const [matchData, setMatchData] = useState({
    date: "",
    type: "",
    result: "",
    teamAScore: "",
    teamBScore: "",
    groundId: "",
    tournamentId: "",
    teamAId: "",
    teamBId: ""
  });
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchMatches();
  }, []);

  const fetchMatches = async () => {
    try {
      const data = await getMatches();
      setMatches(data);
    } catch (error) {
      console.error('Error fetching matches', error);
    }
  };

  const handleClose = () => {
    setShow(false);
    setError(null);
  };

  const handleShow = (match ) => {
    if (match) {
      setEditingMatch(match);
      setMatchData(match);
    } else {
      setEditingMatch(null);
      setMatchData({
        date: "",
        type: "",
        result: "",
        teamAScore: "",
        teamBScore: "",
        groundId: "",
        tournamentId: "",
        teamAId: "",
        teamBId: ""
      });
    }
    setShow(true);
  };

  const handleSave = async () => {
    if (!matchData.date || !matchData.type || !matchData.result) {
      setError("Date, type, and result are required.");
      return;
    }

    try {
      if (editingMatch) {
        await updateMatch(editingMatch.id, matchData);
        setMatches(matches.map(match => match.id === editingMatch.id ? { ...match, ...matchData } : match));
      } else {
        const newMatch = await createMatch(matchData);
        setMatches([...matches, newMatch]);
      }
      handleClose();
    } catch (error) {
      console.error('Error saving match', error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteMatch(id);
      setMatches(matches.filter(match => match.id !== id));
    } catch (error) {
      console.error('Error deleting match', error);
    }
  };

  return (
    <Container>
      <h2>Manage Matches</h2>
      <Button variant="success" className="mb-3" onClick={() => handleShow()}>Add New Match</Button>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>#</th>
            <th>Date</th>
            <th>Type</th>
            <th>Result</th>
            <th>Team A Score</th>
            <th>Team B Score</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {matches.map((match, index) => (
            <tr key={index}>
              <td>{match.id}</td>
              <td>{match.date}</td>
              <td>{match.type}</td>
              <td>{match.result}</td>
              <td>{match.teamAScore}</td>
              <td>{match.teamBScore}</td>
              <td>
                <Button variant="warning" className="me-2" onClick={() => handleShow(match)}>Edit</Button>
                <Button variant="danger" onClick={() => handleDelete(match.id)}>Delete</Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>

      {/* Modal for Adding/Editing Match */}
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>{editingMatch ? 'Edit Match' : 'Add New Match'}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {error && <Alert variant="danger">{error}</Alert>}
          <Form>
            <Form.Group controlId="formDate">
              <Form.Label>Date</Form.Label>
              <Form.Control 
                type="date" 
                placeholder="Enter match date" 
                value={matchData.date} 
                onChange={(e) => setMatchData({ ...matchData, date: e.target.value })} 
              />
            </Form.Group>
            <Form.Group controlId="formType">
              <Form.Label>Type</Form.Label>
              <Form.Control 
                type="text" 
                placeholder="Enter match type" 
                value={matchData.type} 
                onChange={(e) => setMatchData({ ...matchData, type: e.target.value })} 
              />
            </Form.Group>
            <Form.Group controlId="formResult">
              <Form.Label>Result</Form.Label>
              <Form.Control 
                type="text" 
                placeholder="Enter match result" 
                value={matchData.result} 
                onChange={(e) => setMatchData({ ...matchData, result: e.target.value })} 
              />
            </Form.Group>
            <Form.Group controlId="formTeamAScore">
              <Form.Label>Team A Score</Form.Label>
              <Form.Control 
                type="text" 
                placeholder="Enter score for team A" 
                value={matchData.teamAScore} 
                onChange={(e) => setMatchData({ ...matchData, teamAScore: e.target.value })} 
              />
            </Form.Group>
            <Form.Group controlId="formTeamBScore">
              <Form.Label>Team B Score</Form.Label>
              <Form.Control 
                type="text" 
                placeholder="Enter score for team B" 
                value={matchData.teamBScore} 
                onChange={(e) => setMatchData({ ...matchData, teamBScore: e.target.value })} 
              />
            </Form.Group>
            <Form.Group controlId="formGroundId">
              <Form.Label>Ground ID</Form.Label>
              <Form.Control 
                type="text" 
                placeholder="Enter ground ID" 
                value={matchData.groundId} 
                onChange={(e) => setMatchData({ ...matchData, groundId: e.target.value })} 
              />
            </Form.Group>
            <Form.Group controlId="formTournamentId">
              <Form.Label>Tournament ID</Form.Label>
              <Form.Control 
                type="text" 
                placeholder="Enter tournament ID" 
                value={matchData.tournamentId} 
                onChange={(e) => setMatchData({ ...matchData, tournamentId: e.target.value })} 
              />
            </Form.Group>
            <Form.Group controlId="formTeamAId">
              <Form.Label>Team A ID</Form.Label>
              <Form.Control 
                type="text" 
                placeholder="Enter team A ID" 
                value={matchData.teamAId} 
                onChange={(e) => setMatchData({ ...matchData, teamAId: e.target.value })} 
              />
            </Form.Group>
            <Form.Group controlId="formTeamBId">
              <Form.Label>Team B ID</Form.Label>
              <Form.Control 
                type="text" 
                placeholder="Enter team B ID" 
                value={matchData.teamBId} 
                onChange={(e) => setMatchData({ ...matchData, teamBId: e.target.value })} 
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleSave}>
            {editingMatch ? 'Save Changes' : 'Add Match'}
          </Button>
        </Modal.Footer>
      </Modal>
      </Container>
  );
}

export default MatchManagement;
