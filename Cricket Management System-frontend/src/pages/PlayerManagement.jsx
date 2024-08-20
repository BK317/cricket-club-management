import React, { useState, useEffect } from 'react';
import { Container, Table, Button, Modal, Form, Alert } from 'react-bootstrap';
import { getPlayers, addPlayer, updatePlayer, deletePlayer, approvePlayer, rejectPlayer } from '../services/playerService'; // Adjust the import path if necessary



const PlayerManagement = () => {
  const [players, setPlayers] = useState([]);
  const [show, setShow] = useState(false);
  const [editingPlayer, setEditingPlayer] = useState(null);
  const [playerData, setPlayerData] = useState({
    name: "",
    position: "",
    team: "",
    battingStyle: "",
    bowlingStyle: "",
    isApproved: false
  });
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchPlayers = async () => {
      try {
        const fetchedPlayers = await getPlayers();
        setPlayers(fetchedPlayers);
      } catch (error) {
        setError('Error fetching players');
      }
    };

    fetchPlayers();
  }, []);

  const handleClose = () => {
    setShow(false);
    setError(null);
  };

  const handleShow = (player = null) => {
    if (player) {
      setEditingPlayer(player);
      setPlayerData(player);
    } else {
      setEditingPlayer(null);
      setPlayerData({
        name: "",
        position: "",
        team: "",
        battingStyle: "",
        bowlingStyle: "",
        isApproved: false
      });
    }
    setShow(true);
  };

  const handleSave = async () => {
    if (playerData.name.trim() === "" || playerData.position.trim() === "") {
      setError("Name and Position are required.");
      return;
    }

    try {
      if (editingPlayer) {
        // Update player
        await updatePlayer(editingPlayer.id, playerData);
        setPlayers(players.map(player => player.id === editingPlayer.id ? { ...player, ...playerData } : player));
      } else {
        // Add new player
        const newPlayer = await addPlayer(playerData);
        setPlayers([...players, newPlayer]);
      }
      handleClose();
    } catch (error) {
      setError('Error saving player');
    }
  };

  const handleDelete = async (id) => {
    try {
      await deletePlayer(id);
      setPlayers(players.filter(player => player.id !== id));
    } catch (error) {
      setError('Error deleting player');
    }
  };

  const handleApprove = async (id) => {
    try {
      const updatedPlayer = await approvePlayer(id);
      setPlayers(players.map(player => player.id === id ? updatedPlayer : player));
    } catch (error) {
      setError('Error approving player');
    }
  };

  const handleReject = async (id) => {
    try {
      const updatedPlayer = await rejectPlayer(id);
      setPlayers(players.map(player => player.id === id ? updatedPlayer : player));
    } catch (error) {
      setError('Error rejecting player');
    }
  };

  return (
    <Container>
      <h2>Manage Players</h2>
      {/* <Button variant="success" className="mb-3" onClick={() => handleShow()}>Add New Player</Button> */}
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>#</th>
            <th>Name</th>
            <th>Position</th>
            <th>Team</th>
            <th>Batting Style</th>
            <th>Bowling Style</th>
            <th>Approved</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {players.map((player) => (
            <tr key={player.id}>
              <td>{player.id}</td>
              <td>{player.name}</td>
              <td>{player.position}</td>
              <td>{player.team}</td>
              <td>{player.battingStyle}</td>
              <td>{player.bowlingStyle}</td>
              <td>{player.accountStatus ==="APPROVED" ? 'Yes' : 'No'}</td>
              <td>
              {player.accountStatus === "UNAPPROVED" ? (
                  <>
                    <Button variant="success" className="me-2" onClick={() => handleApprove(player.id)}>Approve</Button>
                    <Button variant="danger" onClick={() => handleReject(player.id)}>Reject</Button>
                  </>
                ) : (
                  <>
                    <Button variant="warning" className="me-2" onClick={() => handleShow(player)}>Edit</Button>

                  </>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </Table>

      {/* Modal for Adding/Editing Player */}
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>{editingPlayer ? 'Edit Player' : 'Add New Player'}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {error && <Alert variant="danger">{error}</Alert>}
          <Form>
            <Form.Group controlId="formPlayerName">
              <Form.Label>Name</Form.Label>
              <Form.Control 
                type="text" 
                placeholder="Enter player name" 
                value={playerData.name} 
                onChange={(e) => setPlayerData({ ...playerData, name: e.target.value })} 
              />
            </Form.Group>
            <Form.Group controlId="formPlayerPosition">
              <Form.Label>Position</Form.Label>
              <Form.Control 
                type="text" 
                placeholder="Enter player position" 
                value={playerData.position} 
                onChange={(e) => setPlayerData({ ...playerData, position: e.target.value })} 
              />
            </Form.Group>
            <Form.Group controlId="formPlayerTeam">
              <Form.Label>Team</Form.Label>
              <Form.Control 
                type="text" 
                placeholder="Enter player team" 
                value={playerData.team} 
                onChange={(e) => setPlayerData({ ...playerData, team: e.target.value })} 
              />
            </Form.Group>
            <Form.Group controlId="formPlayerBattingStyle">
              <Form.Label>Batting Style</Form.Label>
              <Form.Control 
                type="text" 
                placeholder="Enter player batting style" 
                value={playerData.battingStyle} 
                onChange={(e) => setPlayerData({ ...playerData, battingStyle: e.target.value })} 
              />
            </Form.Group>
            <Form.Group controlId="formPlayerBowlingStyle">
              <Form.Label>Bowling Style</Form.Label>
              <Form.Control 
                type="text" 
                placeholder="Enter player bowling style" 
                value={playerData.bowlingStyle} 
                onChange={(e) => setPlayerData({ ...playerData, bowlingStyle: e.target.value })} 
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleSave}>
            {editingPlayer ? 'Save Changes' : 'Add Player'}
          </Button>
        </Modal.Footer>
      </Modal>
      </Container>
  );
}

export default PlayerManagement;

