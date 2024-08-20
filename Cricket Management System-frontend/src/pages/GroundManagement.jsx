import React, { useState, useEffect } from 'react';
import { Container, Table, Button, Modal, Form, Alert } from 'react-bootstrap';
import { getGrounds, addGround, updateGround } from '../services/groundServic'



const GroundManagement = () => {
  const [grounds, setGrounds] = useState([]);
  const [show, setShow] = useState(false);
  const [editingGround, setEditingGround] = useState(null);
  const [groundData, setGroundData] = useState({
    name: "",
    location: "",
    capacity: ""
  });
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchGrounds = async () => {
      try {
        const fetchedGrounds = await getGrounds();
        setGrounds(fetchedGrounds);
      } catch (error) {
        setError('Error fetching grounds');
      }
    };

    fetchGrounds();
  }, []);

  const handleClose = () => {
    setShow(false);
    setError(null);
  };

  const handleShow = (ground = null) => {
    if (ground) {
      setEditingGround(ground);
      setGroundData(ground);
    } else {
      setEditingGround(null);
      setGroundData({
        name: "",
        location: "",
        capacity: ""
      });
    }
    setShow(true);
  };

  const handleSave = async () => {
    if (groundData.name.trim() === "" || groundData.location.trim() === "") {
      setError("Name and Location are required.");
      return;
    }

    try {
      if (editingGround) {
        // Update ground
        await updateGround(editingGround.id, groundData);
        setGrounds(grounds.map(ground => ground.id === editingGround.id ? { ...ground, ...groundData } : ground));
      } else {
        // Add new ground
        const newGround = await addGround(groundData);
        setGrounds([...grounds, newGround]);
      }
      handleClose();
    } catch (error) {
      setError('Error saving ground');
    }
  };

  return (
    <Container>
      <h2>Manage Grounds</h2>
      <Button variant="success" className="mb-3" onClick={() => handleShow()}>Add New Ground</Button>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>#</th>
            <th>Name</th>
            <th>Location</th>
            <th>Capacity</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {grounds.map((ground) => (
            <tr key={ground.id}>
              <td>{ground.id}</td>
              <td>{ground.name}</td>
              <td>{ground.location}</td>
              <td>{ground.capacity}</td>
              <td>
                <Button variant="warning" className="me-2" onClick={() => handleShow(ground)}>Edit</Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>

      {/* Modal for Adding/Editing Ground */}
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>{editingGround ? 'Edit Ground' : 'Add New Ground'}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {error && <Alert variant="danger">{error}</Alert>}
          <Form>
            <Form.Group controlId="formGroundName">
              <Form.Label>Name</Form.Label>
              <Form.Control 
                type="text" 
                placeholder="Enter ground name" 
                value={groundData.name} 
                onChange={(e) => setGroundData({ ...groundData, name: e.target.value })} 
              />
            </Form.Group>
            <Form.Group controlId="formGroundLocation" className="mt-2">
              <Form.Label>Location</Form.Label>
              <Form.Control 
                type="text" 
                placeholder="Enter ground location" 
                value={groundData.location} 
                onChange={(e) => setGroundData({ ...groundData, location: e.target.value })} 
              />
            </Form.Group>
            <Form.Group controlId="formGroundCapacity" className="mt-2">
              <Form.Label>Capacity</Form.Label>
              <Form.Control 
                type="number" 
                placeholder="Enter ground capacity" 
                value={groundData.capacity} 
                onChange={(e) => setGroundData({ ...groundData, capacity: e.target.value })} 
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleSave}>
            {editingGround ? 'Save Changes' : 'Add Ground'}
          </Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
}

export default GroundManagement;
