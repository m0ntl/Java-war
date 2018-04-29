
Business Logic:

Master thread
	- New thread for each missile
	Main thread
		- Call asynchronously for each missile/destructor launch
		- Collect statistics
		- class for each side
		- End game when all launchers are destroyed
	1. Missile fired
		1. define - destination, flight time and damage.
		2. Add missile to laucher queue if laucher is not destroyed
		2. if launcher is hidden: Launcher is not hidden for X time
		3. Launcher creates new thread for missile
		4. On thread destruct, check if missile destructed/hit target and check damage - add to main thread count/statistics
	2. Missile destructor fired
		1. on define - time after launch, target missile id, randome flight time
		2. If interceptor destruct time is befire target missile land time
			1. Turn target missile thread to destructed
	3. Launcher destructor fired
		1. on define - time after launch, target launcher id, randome flight time
		2. Id target launcher is hidden, launch fails
